package com.gabriel.customer;

public  record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
        ){
}
