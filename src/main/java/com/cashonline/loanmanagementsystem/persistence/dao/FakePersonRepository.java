package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Person;
import com.jasongoodwin.monads.Try;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePersonRepository implements PersonDAO {
    private final Map<Long, Person> persons;

    public FakePersonRepository() {
        this.persons = new HashMap<>();
    }

    @Override
    public Optional<Person> findPerson(long i) {
        return Optional.ofNullable(this.persons.get(i));
    }

    @Override
    public Try<Person> savePerson(Person p) {
        if (this.persons.putIfAbsent(p.getId(), p) != null) {
            return Try.failure(new PersonAddedTwiceException());
        } else {
            return Try.successful(p);
        }
    }

    @Override
    public void deletePerson(long l) {
        persons.remove(l);
    }

    @Override
    public void updatePerson(Person p) {
        persons.put(p.getId(), p);
    }

    private static class PersonAddedTwiceException extends RuntimeException {
        public PersonAddedTwiceException() {
            super();
        }
    }
}
