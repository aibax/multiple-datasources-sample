package com.example.data.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.data.secondary.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>
{
}