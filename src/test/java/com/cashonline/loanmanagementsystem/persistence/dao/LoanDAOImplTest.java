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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
class LoanDAOImplTest {

    @Autowired
    @Qualifier("LoanRepository")
    LoanDAO loanDAO;

    @Autowired
    EntityManagerFactory emf;

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
    public void whenThereAre4Loans_countIs4(){
        LoanServiceImpl.Page page = new LoanServiceImpl.Page(0, 1);
        LoanServiceImpl.PagedLoans loans = loanDAO.getPagedLoans(page);
        assertEquals(1, loans.page().size());
    }

}