package com.example.customerapi.repositories;

import java.util.UUID;

import com.example.customerapi.models.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
