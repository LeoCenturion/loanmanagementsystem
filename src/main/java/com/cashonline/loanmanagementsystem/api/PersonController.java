package com.cashonline.loanmanagementsystem.api;

import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.service.PersonService;

public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService ps) {
        this.personService = ps;
    }

    public void addPerson(Person person){
        personService.addPerson(person);
    }

}
