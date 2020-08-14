package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.persistence.FakePersonRepository;
import com.cashonline.loanmanagementsystem.persistence.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceImplTest {
    private PersonServiceImpl ps;
    private Person p;


    @BeforeEach
    public void setup(){
        PersonDAO personDAO = new FakePersonRepository();
        p = new Person((long) 1, "","","");
        personDAO.savePerson(p);

        ps = new PersonServiceImpl(personDAO);
    }

    @Test
    public void whenPersonXIsPresent_thenCanGetPersonX(){
        Person  actualPerson = ps.getPerson(1).get();
        assertEquals(p, actualPerson);
    }

    @Test
    public void whenAddedPersonX_thenCanGetPersonX(){
        Person expectedPerson = new Person((long) 2,"","","");
        ps.addPerson(expectedPerson);
        Person  actualPerson = ps.getPerson(2).get();

        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void whenAddedPersonXTwice_thenSecondIsIgnored(){
        Person firstPerson = new Person((long) 2,"","first","");
        Person secondPerson = new Person((long) 2,"","second","");
        ps.addPerson(firstPerson);
        ps.addPerson(secondPerson);

        Person  actualPerson = ps.getPerson(2).get();

        assertEquals("first", actualPerson.getFirstName());
    }

    @Test
    public void whenPersonXDeleted_thenCanNotGetX(){
        ps.removePerson(1);
        Optional<Person> actual = ps.getPerson(1);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void whenPersonXDeleted_thenCanBeDeletedAgain(){
        ps.removePerson(1);
        ps.removePerson(1);
        Optional<Person> actual = ps.getPerson(1);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void whenPersonXUpdated_thenChangesAreReflected(){
        Person p = new Person((long) 1, "updated","","");
        ps.updatePerson(p);
        Optional<Person> actual = ps.getPerson(1);
        assertEquals("updated", actual.get().getEmail());
    }
}