package com.cashonline.loanmanagementsystem.persistence.entities;

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
    private Long id;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borrowerId")
    private List<LoanEntity> loans;

    public PersonEntity(long id, String email, String firstName, String lastName, List<LoanEntity> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public Long getId() {
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
        return new Person(personEntity.id, personEntity.email, personEntity.firstName, personEntity.lastName);
    }

    public Stream<LoanEntity> getLoans() {
        return loans.stream();
    }
}