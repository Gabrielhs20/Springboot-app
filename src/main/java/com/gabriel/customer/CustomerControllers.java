package com.gabriel.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerControllers {

    private final CustomerService customerService;

    //customerService constructor
    public CustomerControllers(CustomerService customerService) {
        this.customerService = customerService;
    }

    //API endpoint, same thing as when we earlier did the GetMapping
    /*@RequestMapping(path = "api/v1/customer", method = RequestMethod.GET)*/
    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("api/v1/customers/{customerId}")
    public Customer getCustomerById(
            @PathVariable("customerId") Integer customerId) {
        return customerService.getCustomerById(customerId);
    }
}
