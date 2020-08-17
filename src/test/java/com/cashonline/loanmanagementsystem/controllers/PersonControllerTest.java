package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.service.PersonServiceImpl;
import com.cashonline.loanmanagementsystem.persistence.FakePersonRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.cashonline.loanmanagementsystem.controllers.dto.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PersonControllerTest {

    @Autowired
    private PersonController pc;


    @Test
    public void whenAddedPerson_thenStatusIsOk() {
        PersonDTO p = new PersonDTO(91, "email", "firstName", "lastName");

        ResponseEntity<?> result = pc.addPerson(p);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void whenPersonAddedTwice_thenStatusIsConflict() {
        PersonDTO p = new PersonDTO(92, "email", "firstName", "lastName");
        pc.addPerson(p);
        ResponseEntity<?> result = pc.addPerson(p);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    public void whenPersonAddedTwice_onlyFirstOneRemains() {
        PersonDTO p = new PersonDTO(93, "email", "firstName", "lastName");
        pc.addPerson(p);
        PersonDTO other = new PersonDTO(p.getId(), "changed", "a", "a");
        pc.addPerson(other);
        ResponseEntity<PersonDTO> result = pc.getPerson(p.getId());
        assertEquals(p.getEmail(), result.getBody().getEmail());
    }

    @Test
    public void whenAddedPerson_thenCanGetPerson() {
        PersonDTO p = new PersonDTO("email", "firstName", "lastName");
        Person personAdded = pc.addPerson(p).getBody();
        ResponseEntity<PersonDTO> expected = pc.getPerson(personAdded.getId());
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExist_thenCannotGetPerson() {
        Long personId = 9999L;
        ResponseEntity<PersonDTO> expected = pc.getPerson(personId);
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());

    }

    @Test
    public void whenPersonExitsAndIsDeleted_thenStatusIsOk() {
        PersonDTO p = new PersonDTO(1, "email", "firstName", "lastName");
        ResponseEntity expected = pc.deletePerson(p.getId());
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

    @Test
    public void whenPersonExistsAndIsDelted_thenCannotGetId() {
        PersonDTO p = new PersonDTO(1, "email", "firstName", "lastName");
        pc.deletePerson(p.getId());
        ResponseEntity expected = pc.getPerson(p.getId());
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExistsAndIsDeleted_thenStatusIsOk() {
        PersonDTO p = new PersonDTO(99999, "email", "firstName", "lastName");
        ResponseEntity expected = pc.deletePerson(p.getId());
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

}