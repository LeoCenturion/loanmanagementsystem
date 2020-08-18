package com.cashonline.loanmanagementsystem;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person p;

    @BeforeEach
    public void setup() {
        p = new Person( 1, "email", "firstName", "lastName");

    }
    @Test
    public void personCreates_thenEmailIsCorrect() {
        assertEquals("email", p.getEmail());
    }

    @Test
    public void personCreates_thenFirstNameIsCorrect() {
        assertEquals("firstName", p.getFirstName());
    }

    @Test
    public void personCreates_thenLastNameIsCorrect() {
        assertEquals("lastName", p.getLastName());
    }

    @Test
    public void personCreates_thenHasNoLoans() {
        assertTrue(p.getLoans().isEmpty());
    }

    @Test
    public void addLoanX_thenIsNotEmpty() {
        Person pWithLoan = p.addLoan(new Loan(1, 0, p.getId()));
        assertFalse(pWithLoan.getLoans().isEmpty());
    }

    @Test
    public void addLoanX_thenHasLoanX() {
        Loan l1 = new Loan(1, 0, p.getId());
        Person pWithLoan = p.addLoan(l1);
        assertTrue(pWithLoan.getLoans().contains(l1));

        Loan l2 = new Loan(2, 0, p.getId());
        Person pWithTwoLoans = pWithLoan.addLoan(l2);
        assertTrue(pWithTwoLoans.getLoans().contains(l2));
    }

    @Test
    public void addLoanXTwice_thenHasLoanXOnce() {
        Loan l1 = new Loan(1, 0, p.getId());
        Person pWithLoan = p.addLoan(l1);
        assertTrue(pWithLoan.getLoans().contains(l1));

        Person pWithTwoLoans = pWithLoan.addLoan(l1);
        assertTrue(pWithTwoLoans.getLoans().contains(l1));
        assertEquals(1, pWithTwoLoans.getLoans().size());
    }

    @Test
    public void removeLoanX_thenDoesntContainX() {
        Loan l1 = new Loan(1, 0, p.getId());
        Loan l2 = new Loan(2, 0, p.getId());
        Person pWithOneLoan = p.addLoan(l1).addLoan(l2).removeLoan(l1);

        assertFalse(pWithOneLoan.getLoans().contains(l1));
    }

    @Test
    public void removeLoanX_thenHasLessLoans() {
        Loan l1 = new Loan(1, 0, p.getId());
        Loan l2 = new Loan(2, 0, p.getId());
        Person pWithOneLoan = p.addLoan(l1).addLoan(l2).removeLoan(l1);

        assertEquals(1, pWithOneLoan.getLoans().size());
    }

    @Test
    public void addLoanBelongingToOther_thenDoesntAddLoan(){
        Loan l1 = new Loan(1, 0, p.getId());
        Loan l2 = new Loan(2, 0, 2);
        Person pWithLoans = p.addLoan(l1).addLoan(l2);

        assertTrue(pWithLoans.getLoans().contains(l1));
        assertFalse(pWithLoans.getLoans().contains(l2));
    }
}
