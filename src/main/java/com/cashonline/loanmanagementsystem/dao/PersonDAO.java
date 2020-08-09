package com.cashonline.loanmanagementsystem.dao;

import com.cashonline.loanmanagementsystem.model.Person;

import java.util.Optional;

public interface PersonDAO {

    Optional<Person> findPerson(long id);

    void savePerson(Person p);

    void deletePerson(long l);

    void updatePerson(Person p);
}
