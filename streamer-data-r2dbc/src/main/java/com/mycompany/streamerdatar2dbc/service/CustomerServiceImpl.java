package com.mycompany.streamerdatar2dbc.service;

import com.mycompany.streamerdatar2dbc.model.Customer;
import com.mycompany.streamerdatar2dbc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Void> saveCustomers(Flux<Customer> customers) {
        return customerRepository.saveAll(customers).then();
    }
}
