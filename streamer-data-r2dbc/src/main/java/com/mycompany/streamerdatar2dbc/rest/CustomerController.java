package com.mycompany.streamerdatar2dbc.rest;

import com.mycompany.streamerdatar2dbc.bus.CustomerStream;
import com.mycompany.streamerdatar2dbc.service.CustomerService;
import com.mycompany.streamerdatar2dbc.service.RandomCustomerGenerator;
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
    public Mono<Void> loadCustomers(@RequestParam int number) {
        return customerService.saveCustomers(randomCustomerGenerator.generate(number));
    }

    @PatchMapping("/stream")
    public Mono<Void> streamCustomers() {
        return customerService.getCustomers().doOnNext(customerStream::send).then();
    }
}
