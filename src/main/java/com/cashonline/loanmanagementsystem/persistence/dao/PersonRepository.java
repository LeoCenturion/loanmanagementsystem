package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Person;
import com.jasongoodwin.monads.Try;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository implements PersonDAO {
    @Override
    public Optional<Person> findPerson(long id) {
        return Optional.empty();
    }

    @Override
    public Try savePerson(Person p) {
        return null;
    }

    @Override
    public void deletePerson(long l) {

    }

    @Override
    public void updatePerson(Person p) {

    }
}
