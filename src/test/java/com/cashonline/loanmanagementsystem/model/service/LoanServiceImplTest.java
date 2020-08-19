package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.persistence.*;
import com.cashonline.loanmanagementsystem.persistence.dao.*;

import org.junit.jupiter.api.*;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;


class LoanServiceImplTest {
    LoanServiceImpl ls;
    PersonService ps;
    Person p = new Person(1, "", "", "");

    @BeforeEach
    void setUp() {
        PersonDAO personDAO = new FakePersonRepository();
        HashMap<Long, Loan> map = new HashMap<>();
        map.put( 1L, new Loan(1, 1, p.getId()));
        map.put( 2L, new Loan(2, 1, p.getId()));
        LoanDAO loanDAO = new FakeLoanRepository(map);

        Person pWithLoans = p.addLoan(new Loan(1, 1, p.getId())).addLoan(new Loan(2, 1, p.getId()));
        personDAO.savePerson(pWithLoans);
        ls = new LoanServiceImpl(personDAO, loanDAO);
        ps = new PersonServiceImpl(personDAO);
    }

    @Test
    public void whenLoanXExists_canGetX() {
        Page page = new Page(1, 99);
        List<Integer> ids = ls.getLoans(page).getItems().stream().map(Loan::getId).collect(toList());
        assertTrue(ids.contains(1));
    }

    @Test
    public void whenLoanXExists_canGetXByPersonId() {
        List<Loan> l = ls.getLoanByPersonId(1);
        List<Integer> ids = l.stream().map(Loan::getId).collect(toList());
        assertTrue(ids.contains(1));
        assertTrue(ids.contains(2));
    }

    @Test
    public void whenLoanIsAddedToPerson_thenCanGetLoanByPersonId(){
        Loan newLoan = new Loan(3, 1, p.getId());
        Person pWithMoreLoans = p.addLoan(newLoan);
        ps.updatePerson(pWithMoreLoans);

        List<Object> loanIds = ls.getLoanByPersonId(pWithMoreLoans.getId()).stream().map(Loan::getId).collect(toList());

        assertTrue(loanIds.contains(newLoan.getId()));
    }
}
