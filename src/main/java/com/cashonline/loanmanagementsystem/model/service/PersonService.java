package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.jasongoodwin.monads.Try;

import java.util.Optional;

public interface PersonService {
     Optional<Person> getPerson(Integer i);

     Try<Person> addPerson(Person person);

     void removePerson(Integer l);

     void updatePerson(Person p);
}
