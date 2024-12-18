package com.gabriel.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //This method with the help of JPA will send a query searching for the email
    boolean existsCustomerByEmail(String email);

    //Check if a customer exists with the specified id
    boolean existsCustomerById(Integer id);
}
