package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.jasongoodwin.monads.Try;
import configuration.PersistenceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
class PersonDAOImplTest {

    @Autowired
    @Qualifier("PersonDAO")
    PersonDAO personDAO;

    @Autowired
    @Qualifier("LoanDAO")
    LoanDAO loanDAO;

    @Test
    public void whenPersonDoesntExist_canAddPerson() {
        Person p = new Person(99, "email", "fistName", "lastName");

        Try actual = personDAO.savePerson(p);

        assertTrue(actual.isSuccess());
    }

    @Test
    public void afterAddingPerson_canGetPerson() {
        Person p = new Person(99, "email", "fistName", "lastName");
        personDAO.savePerson(p);

        Optional<Person> actual = personDAO.findPerson(p.getId());

        assertEquals(p.getId(), actual.get().getId());
    }
    @Test
    public void afterAddingPerson_canDeletePerson(){
        Person p = new Person(99, "email", "fistName", "lastName");
        personDAO.savePerson(p);

        personDAO.deletePerson(p.getId());
        Optional<Person> actual = personDAO.findPerson(p.getId());

        assertTrue(actual.isEmpty());
    }

    @Test
    public void afterGettingPerson_dataIsCorrect() {
        Person expected = new Person(99, "email", "fistName", "lastName")
        .addLoan(new Loan(99, 1, 99))
        .addLoan(new Loan(98, 1, 99))
        .addLoan(new Loan(97, 1, 99));
        personDAO.savePerson(expected);


        Person actual = personDAO.findPerson(expected.getId()).get();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getLoans(), actual.getLoans());

    }

    @Test
    public void whenDeletingPerson_loansDeletedToo() {
        Integer personWithLoansId = 1;
        Optional<Person> personWithLoans = personDAO.findPerson(personWithLoansId);
        assertFalse(personWithLoans.isEmpty());
        List<Loan> loans = personWithLoans.get().getLoans();

        personDAO.deletePerson(personWithLoansId);

        loans.forEach(l -> {
            Optional<Loan> loan = loanDAO.findLoan(l.getId());
            assertTrue(loan.isEmpty());
        });
    }

    @Test
    public void afterAddingPerson_personHasNoLoans(){
        Person p = new Person(99, "email", "fistName", "lastName");

        Try<Person> actual = personDAO.savePerson(p);

        assertTrue(actual.getUnchecked().getLoans().isEmpty());

    }

    @Test
    public void afterDeletingPerson_canAddPersonAgain(){
        Person p = new Person(99, "email", "fistName", "lastName");
        personDAO.savePerson(p);
        personDAO.deletePerson(p.getId());
        personDAO.savePerson(p);

        Optional<Person> actual = personDAO.findPerson(p.getId());
        assertFalse(actual.isEmpty());
    }

}