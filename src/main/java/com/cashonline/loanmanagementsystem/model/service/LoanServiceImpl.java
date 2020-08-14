package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.persistence.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private final PersonDAO personDao;

    private final LoanDAO loanDao;

    @Autowired
    public LoanServiceImpl(PersonDAO personDao, @Qualifier("LoanRepository")  LoanDAO loan) {
        this.personDao = personDao;
        this.loanDao = loan;
    }

    public List<Loan> getLoanByPersonId(Long i) {
        Optional<Person> person = personDao.findPerson(i);
        return person.map(Person::getLoans).orElse(new ArrayList<>());
    }

    public List<Loan> getLoans() {
        return loanDao.getLoans();
    }
}
