package com.gabriel.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{
    //Database
    private static List<Customer>customers;

    static{
        customers = new ArrayList<>();

        Customer isa = new Customer(
                2,
                "Isa",
                "isa123@gmail.com",
                23
        );
        Customer gabriel = new Customer(
                1,
                "Gabriel",
                "gabo20hernandez@gmail.com",
                25
        );
        customers.add(gabriel);
        customers.add(isa);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
