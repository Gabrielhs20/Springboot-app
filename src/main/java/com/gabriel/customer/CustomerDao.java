package com.gabriel.customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);

    void inserCustomer(Customer customer);
    boolean existsPersonWithEmail(String email);
    void deleteCustomerById(Integer id);
    boolean existsPersonWithId(Integer id);
}
