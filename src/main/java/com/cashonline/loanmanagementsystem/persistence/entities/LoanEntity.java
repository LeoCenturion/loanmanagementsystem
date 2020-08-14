package com.cashonline.loanmanagementsystem.persistence.entities;


import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.persistence.dao.LoanDAOImpl;

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
    private Long id;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "borrowerId")
    private PersonEntity borrower;

    private Long borrowerId;
    public static final String GET_LOANS_BY_BORROWER = "LoanEntity.getAllLoansByBorrower";
    static final String GET_LOAN_BY_ID = "LoanEntity.getLoanById";
    public static final String GET_ALL_LOANS = "LoanEntity.getAllLoans";
    static final String COUNT_LOANS = "LoanEntity.countLoans";

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
        return new Loan(loanEntity.getId(), loanEntity.getAmount(), loanEntity.borrowerId);
    }
}
