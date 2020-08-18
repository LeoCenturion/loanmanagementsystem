package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.controllers.dto.LoanDTO;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.model.service.LoanService;

import configuration.PersistenceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = {PersistenceConfig.class})
public class LoanControllerTest {

    @Autowired
    @Qualifier("LoanController")
    LoanController loanController;

    @Test
    public void whenGettingAllLoans_statusIsOk(){
        Page page = new Page(0, 99);

        ResponseEntity<PagedLoans> loans = loanController.getLoans(page);

        assertEquals(HttpStatus.OK, loans.getStatusCode());
    }

    @Test
    public void whenGettingAllLoans_pagingDataIsWellFormatted(){
        Page page = new Page(0, 99);

        PagedLoans loans = loanController.getLoans(page).getBody();

        assertEquals(page.pageNumber(), loans.pageNumber());
        assertEquals(page.pageSize(), loans.pageSize());
        assertNotNull(loans.totalPages());
        assertTrue(loans.totalPages() > 0);
    }


}
