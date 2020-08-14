package com.cashonline.loanmanagementsystem.persistence;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.persistence.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.jasongoodwin.monads.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Qualifier("fakePersonDAO")
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

    @Override
    public PagedLoans getLoansPaged(Long borrowerId, Page page) {
        return null;
    }

    private static class PersonAddedTwiceException extends RuntimeException {
        public PersonAddedTwiceException() {
            super();
        }
    }
}
