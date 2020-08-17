package com.cashonline.loanmanagementsystem.controllers.dto;


import com.cashonline.loanmanagementsystem.model.entities.Loan;

public class LoanDTO {

    private final long id;
    private final int amount;
    private final long borrowerId;

    public LoanDTO(long id, int amount, long borrower) {
        this.id = id;
        this.amount = amount;
        this.borrowerId = borrower;
    }
    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    static public LoanDTO from(Loan loan){
        return new LoanDTO(loan.getId(), loan.getAmount(), loan.getBorrowerId());
    }
}
