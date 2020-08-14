package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cashonline.loanmanagementsystem.controllers.dto.PersonDTO;
import com.cashonline.loanmanagementsystem.model.service.PersonService;

@RequestMapping("api/v1/persons")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService ps) {
        this.personService = ps;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody PersonDTO person) {
        return personService.addPerson(PersonDTO.toPerson(person))
                .map(p -> ResponseEntity.ok().body(p))
                .recover(e -> ResponseEntity.status(HttpStatus.CONFLICT).build());

    }

    public ResponseEntity<PersonDTO> getPerson(long id) {
        return personService.getPerson(id)
                .map(PersonDTO::fromPerson)
                .map(p -> ResponseEntity.ok().body(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity deletePerson(long id) {
        personService.removePerson(id);
        return ResponseEntity.ok().build();
    }
}
