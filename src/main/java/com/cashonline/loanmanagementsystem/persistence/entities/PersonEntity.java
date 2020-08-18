package com.cashonline.loanmanagementsystem.persistence.entities;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

@Entity
@PersistenceContext(name = "myDatasource")
@Table(name = "persons", schema = "MAIN")
public class PersonEntity {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="borrowerId")
    private List<LoanEntity> loans;

    public PersonEntity(Integer id, String email, String firstName, String lastName, List<LoanEntity> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }



    public PersonEntity() {
    }

    public static PersonEntity from(Person p) {
        List<LoanEntity> loans = p.getLoans().stream()
                .map(LoanEntity::from)
                .collect(toList());
        return new PersonEntity(p.getId(), p.getEmail(), p.getFirstName(), p.getLastName(), loans);
    }

    public static Person toPerson(PersonEntity personEntity) {
        List<Loan> loans = personEntity.getLoans().map(LoanEntity::toLoan).collect(toList());
        return new Person(personEntity.id, personEntity.email, personEntity.firstName, personEntity.lastName, loans);
    }

    public Stream<LoanEntity> getLoans() {
        return loans.stream();
    }
}