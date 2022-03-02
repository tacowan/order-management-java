package com.example.customerapi.controllers;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.UUID;

import com.example.customerapi.models.*;
import com.example.customerapi.repositories.CustomerRepository;

@RestController
@RequestMapping("/customers")
@Api(value = "Customer API", tags = "Customers", description = "Operations pertaining to customers", produces = "application/json")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @ApiOperation("Get all customers")
    @GetMapping()
    ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @ApiOperation("Get a customer by id")
    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable("id") UUID id) {
        return customerRepository.findById(id).orElse(null);
    }

    @ApiOperation("Create a customer")
    @PostMapping()
    Customer createCustomer(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID());
        return customerRepository.save(customer);
    }

    @ApiOperation("Update a customer")
    @PutMapping("/{id}")
    Customer updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        customer.setId(id);
        if (customerRepository.findById(id).isPresent()) {
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    @ApiOperation("Delete a customer")
    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable("id") UUID id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        }
    }
}
