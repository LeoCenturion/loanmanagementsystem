package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;
import com.jasongoodwin.monads.Try;

import java.util.Optional;

public interface LoanDAO {

    PagedLoans getLoansPaged(Page page);

    Try saveLoan(LoanEntity loan);

    Optional<Loan> findLoan(long id);
}
