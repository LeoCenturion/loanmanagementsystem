package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;

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

        ResponseEntity<PagedLoans> loans = loanController.getLoans(page.getPageNumber(), page.getPageSize());

        assertEquals(HttpStatus.OK, loans.getStatusCode());
    }

    @Test
    public void whenGettingAllLoans_pagingDataIsWellFormatted(){
        Page page = new Page(0, 99);

        PagedLoans loans = loanController.getLoans(page.getPageNumber(), page.getPageSize()).getBody();

        assertEquals(page.pageNumber(), loans.getPageNumber());
        assertEquals(page.pageSize(), loans.getPageSize());
        assertNotNull(loans.getTotalPages());
        assertTrue(loans.getTotalPages() > 0);
    }


}
