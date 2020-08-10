package com.cashonline.loanmanagementsystem.persistence.entities;

import javax.persistence.*;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String lastName;
    private  String email;
    private  String firstName;


}
