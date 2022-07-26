package com.ivanfranchin.streamerdatar2dbc.rest;

import com.ivanfranchin.streamerdatar2dbc.bus.CustomerStream;
import com.ivanfranchin.streamerdatar2dbc.service.RandomCustomerGenerator;
import com.ivanfranchin.streamerdatar2dbc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerStream customerStream;
    private final RandomCustomerGenerator randomCustomerGenerator;

    @PatchMapping("/load")
    public Mono<Void> loadCustomers(@RequestParam Integer amount) {
        return customerService.saveCustomers(randomCustomerGenerator.generate(amount));
    }

    @PatchMapping("/stream")
    public Mono<Void> streamCustomers() {
        return customerService.getCustomers().doOnNext(customerStream::send).then();
    }
}
