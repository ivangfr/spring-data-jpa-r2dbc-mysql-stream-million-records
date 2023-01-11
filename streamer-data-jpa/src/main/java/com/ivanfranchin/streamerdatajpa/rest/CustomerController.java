package com.ivanfranchin.streamerdatajpa.rest;

import com.ivanfranchin.streamerdatajpa.bus.CustomerStream;
import com.ivanfranchin.streamerdatajpa.model.Customer;
import com.ivanfranchin.streamerdatajpa.service.CustomerService;
import com.ivanfranchin.streamerdatajpa.service.RandomCustomerGenerator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerStream customerStream;
    private final RandomCustomerGenerator randomCustomerGenerator;
    private final EntityManager entityManager;

    @PatchMapping("/load")
    public void loadCustomers(@RequestParam Integer amount) {
        customerService.saveCustomers(randomCustomerGenerator.generate(amount));
    }

    @PatchMapping("/stream-naive")
    public void streamNaiveCustomers() {
        customerService.getCustomerList().forEach(customerStream::send);
    }

    @Transactional(readOnly = true)
    @PatchMapping("/stream")
    public void streamCustomers() {
        try (Stream<Customer> customers = customerService.getCustomerStream()) {
            customers.forEach(customer -> {
                customerStream.send(customer);
                entityManager.detach(customer);
            });
        }
    }
}
