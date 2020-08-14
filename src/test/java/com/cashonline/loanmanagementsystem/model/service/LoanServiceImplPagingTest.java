package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.persistence.FakePersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanServiceImplPagingTest {


    @Autowired
    LoanServiceImpl loanService;

    @Test
    public void whenPageHasMoreElementsThanExisting_thenRetreivesAllElements(){

    }
}