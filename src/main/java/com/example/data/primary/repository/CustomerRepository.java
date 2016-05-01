package com.example.data.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.data.primary.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
}
