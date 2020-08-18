package com.cashonline.loanmanagementsystem.model.entities;

import java.util.Objects;

final public class Loan {
    private final Integer id;
    private final int amount;
    private final Integer borrowerId;

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public Loan(Integer i, int amount, Integer p) {
        this.id = i;
        this.amount = amount;
        this.borrowerId = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
