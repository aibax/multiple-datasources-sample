package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.primary.domain.Customer;
import com.example.data.primary.repository.CustomerRepository;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }
}