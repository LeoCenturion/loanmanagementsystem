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

    public record PagedLoans(List<Loan> page, Integer totalPages){}
    public record Page(Integer pageNumber, Integer pageSize) {}

    private final PersonDAO personDao;
    private final LoanDAO loanDao;

    @Autowired
    public LoanServiceImpl(@Qualifier("PersonDAO") PersonDAO personDao, @Qualifier("LoanDAO")  LoanDAO loan) {
        this.personDao = personDao;
        this.loanDao = loan;
    }

    public List<Loan> getLoanByPersonId(Long i) {
        Optional<Person> person = personDao.findPerson(i);
        return person.map(Person::getLoans).orElse(new ArrayList<>());
    }

    @Override
    public PagedLoans getLoans(Page page) {

        return loanDao.getPagedLoans(page);
    }
}
