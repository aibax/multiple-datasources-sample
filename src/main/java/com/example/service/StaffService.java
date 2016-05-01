package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.secondary.domain.Staff;
import com.example.data.secondary.repository.StaffRepository;

@Service
public class StaffService
{
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getStaffs()
    {
        return staffRepository.findAll();
    }
}