package com.mycompany.streamerdatajpa.service;

import com.mycompany.streamerdatajpa.model.Customer;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomCustomerGenerator {

    private final Random random = new SecureRandom();

    private static final List<String> FIRST_NAMES = List.of(
            "Liam", "Noah", "Oliver", "Elijah", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander", "Mason",
            "Michael", "Ethan", "Daniel", "Jacob", "Logan", "Jackson", "Levi", "Sebastian", "Mateo", "Jack", "Owen",
            "Theodore", "Aiden", "Samuel", "Joseph", "John", "David", "Wyatt", "Matthew", "Luke", "Asher", "Carter",
            "Julian", "Grayson", "Leo", "Jayden", "Gabriel", "Isaac", "Lincoln", "Anthony", "Hudson", "Dylan", "Ezra",
            "Thomas", "Charles", "Christopher", "Jaxon", "Maverick", "Josiah"
    );
    private static final List<String> LAST_NAMES = List.of(
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
            "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green",
            "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts"
    );

    public List<Customer> generate(Integer amount) {
        return IntStream.rangeClosed(1, amount)
                .mapToObj(i -> {
                    Customer customer = new Customer();
                    customer.setFirstName(FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size())));
                    customer.setLastName(LAST_NAMES.get(random.nextInt(LAST_NAMES.size())));
                    return customer;
                }).collect(Collectors.toList());
    }
}
