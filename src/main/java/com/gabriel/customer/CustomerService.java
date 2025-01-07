package com.gabriel.customer;

import com.gabriel.exception.DuplicateResourceException;
import com.gabriel.exception.RequestValidationException;
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

        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer customerId){
      //Check if the customerId intended to delete exists
      if(!customerDao.existsPersonWithId(customerId)){
          throw new ResourceNotFoundException(
                  "Customer not found with id: [%s]".formatted(customerId)
          );
      }

      //Delete customer by customerId
      customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {
      Customer customer = getCustomerById(customerId);

      boolean changes = false;

      if(updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
          customer.setName(updateRequest.name());
          changes = true;
      }

      if(updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
          customer.setAge(updateRequest.age());
          changes = true;
      }

      if(updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
          if(customerDao.existsPersonWithEmail(updateRequest.email())){
              throw new DuplicateResourceException(
                      "Email is already in use. Please choose another one!"
              );
          }

          customer.setEmail(updateRequest.email());
          changes = true;
      }

      if(!changes){
          throw new RequestValidationException(
                  "No data changes were found.");
      }
      customerDao.updateCustomer(customer);
    }
}
