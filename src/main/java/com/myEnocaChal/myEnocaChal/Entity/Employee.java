package com.myEnocaChal.myEnocaChal.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name="employees")
public class Employee {

    @Column(name = "employee_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name ;

    @Column(name="age")
    private int age ;

    @Column(name="salary")
    private double salary;

    @Column(name="working_year")
    private int working_year ;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id",referencedColumnName = "company_id" )
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Company company;

}