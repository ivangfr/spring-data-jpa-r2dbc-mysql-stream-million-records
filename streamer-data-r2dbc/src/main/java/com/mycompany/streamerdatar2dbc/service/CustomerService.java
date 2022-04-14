package com.mycompany.streamerdatar2dbc.service;

import com.mycompany.streamerdatar2dbc.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<Customer> getCustomers();

    Mono<Void> saveCustomers(Flux<Customer> customers);
}
