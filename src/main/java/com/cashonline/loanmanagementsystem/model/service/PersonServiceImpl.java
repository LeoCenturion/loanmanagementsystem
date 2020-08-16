package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.persistence.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.jasongoodwin.monads.Try;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(@Qualifier("PersonDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> getPerson(long i) {
        return personDAO.findPerson(i);
    }

    public Try addPerson(Person person) {
        return personDAO.findPerson(person.getId())
                .map(p -> Try.failure(new PersonInExistenceException()))
                .orElseGet(() -> personDAO.savePerson(person));

    }

    public void removePerson(long l) {
        try {
            personDAO.deletePerson(l);
        }
        catch(Exception e){
            LoggerFactory.getLogger(PersonServiceImpl.class).warn("Removing person failed, id="+l);
        }

    }

    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }

    private static class PersonInExistenceException extends Throwable {
    }
}
