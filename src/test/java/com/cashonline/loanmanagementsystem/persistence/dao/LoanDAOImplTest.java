package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;
import configuration.PersistenceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
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

    @BeforeEach
    @Transactional
    public void setup(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        LoanEntity le = new LoanEntity();
        le.setAmount(1);
        le.setBorrowerId((long) 1);
        le.setId((long) 1);

        em.persist(le);
        em.flush();
        t.commit();
        em.close();

    }
    @Test
    public void whenLoansExists_thenCanGetAllLoans(){
        List<Loan> loans = loanDAO.getLoans();
        assertFalse(loans.isEmpty());
    }

}