package com.mycompany.streamerdatajpa.service;

import com.mycompany.streamerdatajpa.model.Customer;

import java.util.List;
import java.util.stream.Stream;

public interface CustomerService {

    List<Customer> getCustomerList();

    Stream<Customer> getCustomerStream();

    void saveCustomers(List<Customer> customers);
}
