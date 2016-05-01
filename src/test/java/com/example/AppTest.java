package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.data.primary.domain.Customer;
import com.example.data.primary.repository.CustomerRepository;
import com.example.data.secondary.domain.Staff;
import com.example.data.secondary.repository.StaffRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class AppTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void testCustomerRepositoryAutowired()
    {
        assertNotNull(customerRepository);
    }

    @Test
    public void testStaffRepositoryAutowired()
    {
        assertNotNull(staffRepository);
    }

    @Test
    public void testCustomerRepositoryFindAll()
    {
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
        assertTrue(customers.size() > 0);

        customers.forEach(System.out::println);
    }

    @Test
    public void testStaffRepositoryFindAll()
    {
        List<Staff> staffs = staffRepository.findAll();
        assertNotNull(staffs);
        assertTrue(staffs.size() > 0);

        staffs.forEach(System.out::println);
    }
}
