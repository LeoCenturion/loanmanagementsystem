package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.jasongoodwin.monads.Try;

import java.util.Optional;

public interface PersonDAO {

    Optional<Person> findPerson(long id);

    Try savePerson(Person p);

    void deletePerson(long l);

    void updatePerson(Person p);

    PagedLoans getLoansPaged(Long borrowerId, Page page);
}
