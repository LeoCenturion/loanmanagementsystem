package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.service.PersonServiceImpl;
import com.cashonline.loanmanagementsystem.persistence.FakePersonRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cashonline.loanmanagementsystem.controllers.dto.*;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    private PersonController pc;
    private PersonDTO p;

    @BeforeEach()
    public void setup(){
        p = new PersonDTO( 1, "email", "firstName", "lastName");
        pc = new PersonController(new PersonServiceImpl(new FakePersonRepository()));
    }

    @Test
    public void whenAddedPerson_thenStatusIsOk(){
        ResponseEntity<?> result = pc.addPerson(p);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void whenPersonAddedTwice_thenStatusIsConflict(){
        pc.addPerson(p);
        ResponseEntity<?> result = pc.addPerson(p);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    public void whenPersonAddedTwie_onlyFirstOneRemains(){
        pc.addPerson(p);
        PersonDTO other = new PersonDTO(p.getId(),"changed","","");
        pc.addPerson(other);
        ResponseEntity<PersonDTO> result = pc.getPerson(p.getId());
        assertEquals(p.getEmail(), result.getBody().getEmail());
    }

    @Test
    public void whenAddedPerson_thenCanGetPerson(){
        pc.addPerson(p);
        ResponseEntity<PersonDTO> expected = pc.getPerson(p.getId());
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExist_thenCannotGetPerson(){
        ResponseEntity<PersonDTO> expected = pc.getPerson(p.getId());
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());

    }

    @Test
    public void whenPersonExitsAndIsDeleted_thenStatusIsOk(){
        ResponseEntity expected = pc.deletePerson(p.getId());
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

    @Test
    public void whenPersonExistsAndIsDelted_thenCannotGetId(){
        pc.deletePerson(p.getId());
        ResponseEntity expected = pc.getPerson(p.getId());
        assertEquals(HttpStatus.NOT_FOUND, expected.getStatusCode());
    }

    @Test
    public void whenPersonDoesntExistsAndIsDeleted_thenStatusIsOk(){
        ResponseEntity expected = pc.deletePerson(2);
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }

}