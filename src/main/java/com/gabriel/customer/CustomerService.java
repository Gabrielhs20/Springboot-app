package com.gabriel.customer;

import com.gabriel.exception.DuplicateResourceException;
import com.gabriel.exception.ResourceNotFoundException;
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
              .orElseThrow(() -> new ResourceNotFoundException(
                      "Customer not found with id: [%s]" .formatted(id)
              ));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        //Check if an account with that email exists
        String email = customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email)){
            throw new DuplicateResourceException(
                    "Email address already in use. Please choose another one."
            );
        }

        //Add customer
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );

        customerDao.inserCustomer(customer);
    }

    public void deleteCustomer(Integer customerId){
      //Check if the customerId intended to delete exists
      if(!customerDao.existsPersonWithId(customerId)){
          throw new ResourceNotFoundException(
                  "Customer not found with id: [%s]".formatted(customerId)
          );
      };

      //Delete customer by customerId
      customerDao.deleteCustomerById(customerId);
    }
}
