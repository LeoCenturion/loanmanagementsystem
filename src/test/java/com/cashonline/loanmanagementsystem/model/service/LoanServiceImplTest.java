package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.persistence.FakeLoanRepository;
import com.cashonline.loanmanagementsystem.persistence.FakePersonRepository;
import com.cashonline.loanmanagementsystem.persistence.dao.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;


class LoanServiceImplTest {
    LoanServiceImpl ls;
    Person p = new Person((long) 1, "", "", "");

    @BeforeEach
    void setUp() {
        PersonDAO personDAO = new FakePersonRepository();
        HashMap<Long, Loan> map = new HashMap<>();
        map.put( 1L, new Loan(1, 1, p));
        map.put( 2L, new Loan(2, 1, p));
        LoanDAO loanDAO = new FakeLoanRepository(map);

        Person pWithLoans = p.addLoan(new Loan(1, 1, p)).addLoan(new Loan(2, 1, p));
        personDAO.savePerson(pWithLoans);
        ls = new LoanServiceImpl(personDAO, loanDAO);
    }

    @Test
    public void whenLoanXExists_canGetX() {
        List<Long> ids = ls.getLoans().stream().map(Loan::getId).collect(toList());
        assertTrue(ids.contains(1L));
    }

    @Test
    public void whenLoanXExists_canGetXByPersonId() {
        List<Loan> l = ls.getLoanByPersonId(1);
        List<Long> ids = l.stream().map(Loan::getId).collect(toList());
        assertTrue(ids.contains(1L));
        assertTrue(ids.contains(2L));
    }




}
