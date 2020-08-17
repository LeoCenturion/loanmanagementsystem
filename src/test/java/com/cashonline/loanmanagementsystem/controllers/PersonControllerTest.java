package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.controllers.dto.*;

import configuration.PersistenceConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
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
    public void whenAddedPerson_thenGetStatusIsOK() {
        PersonDTO p = new PersonDTO(9999L, "email", "firstName", "lastName");
        Person personAdded = pc.addPerson(p).getBody();
        ResponseEntity<PersonDTO> actual = pc.getPerson(personAdded.getId());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExist_thenGetStatusIsNotFound() {
        Long personId = 9999L;
        ResponseEntity<PersonDTO> actual = pc.getPerson(personId);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());

    }

    @Test
    public void whenPersonExitsAndIsDeleted_thenStatusIsOk() {
        PersonDTO p = new PersonDTO(1, "email", "firstName", "lastName");
        ResponseEntity actual = pc.deletePerson(p.getId());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void whenPersonExistsAndIsDelted_thenGetStatusIsNotFound() {
        PersonDTO p = new PersonDTO(1, "email", "firstName", "lastName");
        pc.deletePerson(p.getId());
        ResponseEntity actual = pc.getPerson(p.getId());
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExistsAndIsDeleted_thenStatusIsOk() {
        PersonDTO p = new PersonDTO(99999, "email", "firstName", "lastName");
        ResponseEntity actual = pc.deletePerson(p.getId());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void afterGettingPerson_thenDataIsCorrect(){
        List<LoanDTO> loans = List.of(
                new LoanDTO(2, 1, 1L),
                new LoanDTO(3, 1, 1L),
                new LoanDTO(4, 1, 1L)
        );
        PersonDTO expected = new PersonDTO(1L, "a", "pepe", "argento", loans);

        ResponseEntity<PersonDTO> response = pc.getPerson(expected.getId());
        PersonDTO actual = response.getBody();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(loans, actual.getLoans());
    }


}