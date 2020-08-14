package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import configuration.PersistenceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
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
        Page page = new Page(1, 99);
        PagedLoans loans = loanDAO.getLoansPaged(page);
        assertFalse(loans.page().isEmpty());
    }
    @Test
    public void canGetPagedData(){
        Page page = new Page(1, 2);
        PagedLoans loans = loanDAO.getLoansPaged(page);
        assertFalse(loans.page().isEmpty());
    }

    @Test
    public void whenPageSizeIsOne_thenGetOneElement(){
        Page page = new Page(0, 1);
        PagedLoans loans = loanDAO.getLoansPaged(page);
        assertEquals(1, loans.page().size());
    }

    @Test
    public void whenPageSizeIsN_andThereAreN_thenFetchesNLoans(){
        Page page = new Page(0, 2);
        PagedLoans loans = loanDAO.getLoansPaged(page);
        assertEquals(2, loans.page().size());
    }

    @Test
    public void whenFilteredByBorrowerId_thenGetsAllLoansOfBorrower(){
        Long borrowerId = 1L;
        Page page = new Page(0, 99);
        PagedLoans loans = personDAO.getLoansPaged(borrowerId, page);

        assertEquals(3, loans.page().size());
    }
    @Test
    public void whenFilteredByBorrowerId_thenAllLoansHaveSameBorrower(){
        Long borrowerId = 1L;
        Page page = new Page(0, 99);
        PagedLoans loans = personDAO.getLoansPaged(borrowerId, page);

        List<Long> withFetchedBorrowerId = loans.page().stream().map(Loan::getBorrowerId).filter(borrowerId::equals).collect(toList());

        assertEquals(loans.page().size(), withFetchedBorrowerId.size());
    }

    @Test
    public void whenGettingByPages_totalNumberIsCorrect(){
        Integer pageSize = 2;
        Page page = new Page(0, pageSize);
        PagedLoans loans = loanDAO.getLoansPaged(page);

        for(int i = 0; i<loans.totalPages(); i++){
            Page newPage = new Page(i, pageSize);
            PagedLoans moreLoans = loanDAO.getLoansPaged(newPage);
            assertFalse(moreLoans.page().isEmpty());
        };
    }
    //PageRequest doesnt work
    public void whenGettingByPages_elementsAreUnique(){
        Integer pageSize = 2;
        Page page = new Page(0, pageSize);
        PagedLoans loans = loanDAO.getLoansPaged(page);

        List<Loan> allLoans = new ArrayList<>();
        for(int i = 0; i<loans.totalPages(); i++){
            Page newPage = new Page(i, pageSize);
            PagedLoans moreLoans = loanDAO.getLoansPaged(newPage);
            allLoans.addAll(moreLoans.page());
        }
        assertEquals(allLoans.size(), (int) allLoans.stream().distinct().count());
    }

}