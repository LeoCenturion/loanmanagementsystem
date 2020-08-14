package com.cashonline.loanmanagementsystem.persistence.entities;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

@Entity
@PersistenceContext(name = "myDatasource")
@Table(name = "persons", schema = "MAIN")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String lastName;
    private  String email;
    private  String firstName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borrowerId")
    private List<LoanEntity> loans;


    public Stream<LoanEntity> getLoans() {
        return loans.stream();
    }

}
