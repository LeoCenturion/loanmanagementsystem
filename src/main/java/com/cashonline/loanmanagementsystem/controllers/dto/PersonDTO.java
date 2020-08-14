package com.cashonline.loanmanagementsystem.controllers.dto;

import com.cashonline.loanmanagementsystem.model.entities.Person;

public class PersonDTO {

    private final long id;
    private final String email;
    private final String firstName;
    private final String lastName;

    public PersonDTO(long l, String email, String firstName, String lastName) {
        this.id = l;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static PersonDTO fromPerson(Person p) {
        return new PersonDTO(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName());
    }

    public static Person toPerson(PersonDTO p) {
        return new Person(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName());
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
