package com.mycompany.streamerdatajpa.service;

import com.mycompany.streamerdatajpa.model.Customer;
import com.mycompany.streamerdatajpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Stream<Customer> getCustomerStream() {
        return customerRepository.streamAll();
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }
}
