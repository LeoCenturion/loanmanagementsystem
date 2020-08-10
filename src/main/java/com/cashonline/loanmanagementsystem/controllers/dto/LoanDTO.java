package com.cashonline.loanmanagementsystem.controllers.dto;


public class LoanDTO {

    private final long id;
    private final int amount;
    private final PersonDTO borrower;

    public LoanDTO(long id, int amount, PersonDTO borrower) {
        this.id = id;
        this.amount = amount;
        this.borrower = borrower;
    }
    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public PersonDTO getBorrower() {
        return borrower;
    }

}
