package com.gabriel;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.gabriel.customer.Customer;
import com.gabriel.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        //printBeans(context);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args ->{
            Customer isa = new Customer(
                    "Isa",
                    "isa123@gmail.com",
                    23
            );
            Customer gabriel = new Customer(
                    "Gabriel",
                    "gabo20hernandez@gmail.com",
                    25
            );
            List<Customer> customers = List.of(isa, gabriel);
            customerRepository.saveAll(customers);

        };
    }
}
