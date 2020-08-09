package com.cashonline.loanmanagementsystem;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Person p;

    @BeforeEach
    public void setup(){
        p = new Person(new Long(1),"email", "firstName", "lastName");
    }

    @Test
    public void createLoan_thenIdIsCorrect(){
        Loan l1 = new Loan(1, 2500, p);
        assertEquals(1, l1.getId());
    }


}