package com.ivanfranchin.streamerdatajpa.customer;

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

    private final CustomerRepository customerRepository;
    private final CustomerEmitter customerStream;
    private final RandomCustomerGenerator randomCustomerGenerator;
    private final EntityManager entityManager;

    @PatchMapping("/load")
    public void loadCustomers(@RequestParam Integer amount) {
        customerRepository.saveAll(randomCustomerGenerator.generate(amount));
    }

    @PatchMapping("/stream-naive")
    public void streamNaiveCustomers() {
        customerRepository.findAll().forEach(customerStream::send);
    }

    @Transactional(readOnly = true)
    @PatchMapping("/stream")
    public void streamCustomers() {
        try (Stream<Customer> customers = customerRepository.streamAll()) {
            customers.forEach(customer -> {
                customerStream.send(customer);
                entityManager.detach(customer);
            });
        }
    }
}
