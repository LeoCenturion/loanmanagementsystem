package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.controllers.dto.PersonDTO;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.jasongoodwin.monads.Try;
import configuration.PersistenceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
class PersonDAOImplTest {

    @Autowired
    @Qualifier("PersonDAO")
    PersonDAO personDAO;

    @Test
    private void whenPersonDoesnExist_canAddPerson(){
        Person p = new Person(99L, "email","fistName","lastName");
        Try actual = personDAO.savePerson(p);
        assertTrue(actual.isSuccess());
    }

}