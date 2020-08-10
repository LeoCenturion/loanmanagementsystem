package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Person;
import com.jasongoodwin.monads.Try;

import java.util.Optional;

public interface PersonService {
     Optional<Person> getPerson(long i);

     Try<Person> addPerson(Person person);

     void removePerson(long l);

     void updatePerson(Person p);
}
