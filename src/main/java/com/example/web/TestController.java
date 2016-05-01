package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.primary.domain.Customer;
import com.example.data.secondary.domain.Staff;
import com.example.service.CustomerService;
import com.example.service.StaffService;

@RestController
public class TestController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/customer")
    public List<Customer> getCustomers()
    {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/staff")
    public List<Staff> getStaffs()
    {
        return staffService.getStaffs();
    }
}