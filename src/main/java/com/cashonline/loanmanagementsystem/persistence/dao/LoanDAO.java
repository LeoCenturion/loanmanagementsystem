package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.jasongoodwin.monads.Try;

import java.util.List;

public interface LoanDAO {

    List<Loan> getLoans();
}
