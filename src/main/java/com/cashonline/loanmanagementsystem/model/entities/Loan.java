package com.cashonline.loanmanagementsystem.model.entities;

import java.util.Objects;

final public class Loan {
    private final long id;
    private final int amount;
    private final Long borrowerId;

    public Long getBorrowerId() {
        return borrowerId;
    }

    public Loan(long i, int amount, Long p) {
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

    public long getId() {
        return id;
    }

}
