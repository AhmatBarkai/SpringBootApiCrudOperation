package com.myEnocaChal.myEnocaChal.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company {

    @Column(name = "company_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name ;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "company_id")
    private List<Employee> employees;



}
