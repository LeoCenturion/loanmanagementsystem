package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cashonline.loanmanagementsystem.controllers.dto.PersonDTO;
import com.cashonline.loanmanagementsystem.model.service.PersonService;

@RestController
@RequestMapping("users")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService ps) {
        this.personService = ps;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO person) {
        return personService.addPerson(PersonDTO.toPerson(person))
                .map(p -> ResponseEntity.ok().body(PersonDTO.fromPerson(p)))
                .recover(e -> ResponseEntity.status(HttpStatus.CONFLICT).build());

    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Integer id) {
        return personService.getPerson(id)
                .map(PersonDTO::fromPerson)
                .map(p -> ResponseEntity.ok().body(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity deletePerson(Integer id) {
        personService.removePerson(id);
        return ResponseEntity.ok().build();
    }
}
