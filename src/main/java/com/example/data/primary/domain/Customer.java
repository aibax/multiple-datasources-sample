package com.example.data.primary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer
{
    @Id
    @GeneratedValue
    private Integer customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;
}