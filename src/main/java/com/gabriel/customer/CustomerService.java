package com.gabriel.customer;

import com.gabriel.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private final CustomerDao customerDao;

  //Constructor for customerDao
  public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
      this.customerDao = customerDao;
  }

    //Method to return a list of all the Customers within the DB
    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    //Method to get the id of the Customer if it doesn't exist, throw an exception
    public Customer getCustomerById(Integer id){
      return customerDao.selectCustomerById(id)
              .orElseThrow(() -> new ResourceNotFound(
                      "Customer not found with id: [%s]" .formatted(id)
              ));
    }
}
