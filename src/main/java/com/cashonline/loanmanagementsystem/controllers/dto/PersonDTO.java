package com.cashonline.loanmanagementsystem.controllers.dto;

import com.cashonline.loanmanagementsystem.model.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class PersonDTO {

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLoans(List<LoanDTO> loans) {
        this.loans = loans;
    }

    private Integer id;
    private  String email;
    private  String firstName;
    private  String lastName;
    private  List<LoanDTO> loans;

    public PersonDTO(Integer id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = new ArrayList<>();
    }

    public PersonDTO(Integer id, String email, String firstName, String lastName, List<LoanDTO> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public PersonDTO() {
    }


    public static PersonDTO fromPerson(Person p) {
        List<LoanDTO> loans = p.getLoans().stream().map(LoanDTO::from).collect(toList());
        return new PersonDTO(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName(), loans);
    }

    public static Person toPerson(PersonDTO p) {
        return new Person(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName());
    }

    public Integer getId() {
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

    public List<LoanDTO> getLoans() {
        return loans;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id &&
                Objects.equals(email, personDTO.email) &&
                Objects.equals(firstName, personDTO.firstName) &&
                Objects.equals(lastName, personDTO.lastName);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
