package com.gabriel.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao{

    private final CustomerRepository customerRepository;

    //Constructor
    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Returns a list of all the customers in the database
    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    //Returns a specific user by specifying an id
    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return Optional.empty();
    }
}
