package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.persistence.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.Person;
import com.jasongoodwin.monads.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> getPerson(long i) {
        return personDAO.findPerson(i);
    }

        public Try<Person> addPerson(Person person) {
        return personDAO.savePerson(person);
    }

    public void removePerson(long l) {
        personDAO.deletePerson(l);
    }

    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }
}
