package com.ivanfranchin.streamerdatar2dbc.customer;

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

    private final CustomerRepository customerRepository;
    private final CustomerEmitter customerStream;
    private final RandomCustomerGenerator randomCustomerGenerator;

    @PatchMapping("/load")
    public Mono<Void> loadCustomers(@RequestParam Integer amount) {
        return customerRepository.saveAll(randomCustomerGenerator.generate(amount)).then();
    }

    @PatchMapping("/stream")
    public Mono<Void> streamCustomers() {
        return customerRepository.findAll().doOnNext(customerStream::send).then();
    }
}
