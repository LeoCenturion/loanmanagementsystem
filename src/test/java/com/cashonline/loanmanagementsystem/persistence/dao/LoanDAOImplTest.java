package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.service.LoanServiceImpl;
import configuration.PersistenceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManagerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
class LoanDAOImplTest {

    @Autowired
    @Qualifier("LoanDAO")
    LoanDAO loanDAO;

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    @Qualifier("PersonDAO")
    private PersonDAO personDAO;

    @Test
    public void whenLoansExists_thenCanGetAllLoans(){
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(1, 99);
        LoanServiceImpl.PagedLoans loans = loanDAO.getPagedLoans(page);
        assertFalse(loans.page().isEmpty());
    }
    @Test
    public void canGetPagedData(){
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(1, 2);
        LoanServiceImpl.PagedLoans loans = loanDAO.getPagedLoans(page);
        assertFalse(loans.page().isEmpty());
    }

    @Test
    public void whenPageSizeIsOne_thenGetOneElemen(){
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(0, 1);
        LoanServiceImpl.PagedLoans loans = loanDAO.getPagedLoans(page);
        assertEquals(1, loans.page().size());
    }

    @Test
    public void whenPageSizeIsN_andThereAreN_thenFetchesNLoans(){
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(0, 2);
        LoanServiceImpl.PagedLoans loans = loanDAO.getPagedLoans(page);
        assertEquals(2, loans.page().size());
    }

    @Test
    public void whenFilteredByBorrowerId_thenAllLoansHaveSameBorrower(){
        Long borrowerId = 1L;
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(0, 99);
        LoanServiceImpl.PagedLoans loans = personDAO.getLoansPaged(borrowerId, page);

        List<Long> withFetchedBorrowerId = loans.page().stream().map(Loan::getBorrowerId).filter(borrowerId::equals).collect(toList());

        assertEquals(loans.page().size(), withFetchedBorrowerId.size());
    }

}