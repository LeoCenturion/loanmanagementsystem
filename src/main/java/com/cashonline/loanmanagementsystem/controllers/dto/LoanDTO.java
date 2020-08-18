package com.cashonline.loanmanagementsystem.controllers.dto;


import com.cashonline.loanmanagementsystem.model.entities.Loan;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDTO loanDTO = (LoanDTO) o;
        return id == loanDTO.id &&
                amount == loanDTO.amount &&
                borrowerId == loanDTO.borrowerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", borrowerId=" + borrowerId +
                '}';
    }
}
