package com.cashonline.loanmanagementsystem.model;

import java.util.Objects;

final public class Loan {
    private final long id;
    private final int amount;
    private final Person borrower;


    public Loan(long i, int amount, Person p) {
        this.id = i;
        this.amount = amount;
        this.borrower = p;
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

    public Person getBorrower() {
        return borrower;
    }
}
