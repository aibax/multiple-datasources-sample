package com.example.data.secondary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
public class Staff
{
    @Id
    @GeneratedValue
    private Integer staffId;

    @Column
    private String name;
}