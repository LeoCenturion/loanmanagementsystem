package com.cashonline.loanmanagementsystem.persistence.entities;


import com.cashonline.loanmanagementsystem.model.entities.Loan;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = LoanEntity.GET_LOAN_BY_ID, query = "select l from LoanEntity l where l.id = :id"),
        @NamedQuery(name = LoanEntity.GET_ALL_LOANS, query = "select l from LoanEntity l"),
//        @NamedQuery(name = LoanEntity.GET_LOANS_BY_BORROWER, query = """
//                select l from LoanEntity l, PersonEntity p
//                where l.borrowerId = p.id
//                and p.id = :id
//                """),
})
@PersistenceContext(name = "myDatasource")
@Table(name = "loans", schema = "MAIN")
public class LoanEntity{
    public static final String GET_LOANS_BY_BORROWER = "LoanEntity.getAllLoansByBorrower";
    static final String GET_LOAN_BY_ID = "LoanEntity.getLoanById";
    public static final String GET_ALL_LOANS = "LoanEntity.getAllLoans";
    static final String COUNT_LOANS = "LoanEntity.countLoans";

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "borrowerId")
    private Long borrowerId;

    public LoanEntity(long id, int amount, Long borrowerId) {
        this.id = id;
        this.amount = amount;
        this.borrowerId = borrowerId;
    }

    public LoanEntity() { }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public static Loan toLoan(LoanEntity loanEntity) {
        return new Loan(loanEntity.getId(), loanEntity.getAmount(), loanEntity.getBorrowerId());
    }

    public static LoanEntity from(Loan loan) {
        return new LoanEntity(loan.getId(), loan.getAmount(), loan.getBorrowerId());
    }
}
