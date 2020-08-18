package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.jasongoodwin.monads.Try;

import java.util.Optional;

public interface PersonDAO {

    Optional<Person> findPerson(Integer id);

    Try savePerson(Person p);

    void deletePerson(Integer l);

    void updatePerson(Person p);

    PagedLoans getLoansPaged(Integer borrowerId, Page page);
}
