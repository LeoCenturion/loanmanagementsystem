package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.persistence.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanServiceImpl {

    private PersonDAO personDao;
    private LoanDAO loanDao;

    public LoanServiceImpl(PersonDAO personDao) {
        this.personDao = personDao;
    }

    public LoanServiceImpl(PersonDAO personDao, LoanDAO loanDao) {
        this.personDao = personDao;
        this.loanDao = loanDao;
    }

    public List<Loan> getLoanByPersonId(int i) {
        Optional<Person> person = personDao.findPerson(i);
        return person.map(Person::getLoans).orElse(new ArrayList<>());
    }

    public List<Loan> getLoans() {
        return loanDao.getLoans();
    }
}
