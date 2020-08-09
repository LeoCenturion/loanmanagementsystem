package com.cashonline.loanmanagementsystem.service;

import com.cashonline.loanmanagementsystem.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.Person;

import java.util.Optional;

public class PersonService {
    private final PersonDAO personDAO;

    public  PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> getPerson(long i) {
        return personDAO.findPerson(i);
    }

    public void addPerson(Person person) {
        personDAO.savePerson(person);
    }

    public void removePerson(long l) {
        personDAO.deletePerson(l);
    }

    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }
}
