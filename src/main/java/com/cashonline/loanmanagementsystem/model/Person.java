package com.cashonline.loanmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Person {
    private final String lastName;
    private final String email;
    private final String firstName;
    private final List<Loan> loans;
    private final Long id;

    public Person(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = new ArrayList<>();
    }

    private Person(Person p,  List<Loan> loans){
        this.id = p.id;
        this.email = p.email;
        this.firstName = p.firstName;
        this.lastName = p.lastName;
        this.loans = List.copyOf(loans);
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

    public List<Loan> getLoans() {
        return List.copyOf(this.loans);
    }

    public Person addLoan(Loan loan) {
        if( !(this.getId() == loan.getBorrowerId())|| loans.contains(loan))
            return this;

        List<Loan> newLoans = new ArrayList<>(loans);
        newLoans.add(loan);
        return new Person(this, newLoans);
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Person removeLoan(Loan l1) {
        ArrayList<Loan> newLoans = new ArrayList<>(loans);
        newLoans.remove(l1);
        return new Person(this, newLoans);
    }

    public long getId() {
        return id;
    }
}
