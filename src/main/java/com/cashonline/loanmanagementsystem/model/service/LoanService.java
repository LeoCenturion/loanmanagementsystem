package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.persistence.dao.Page;

import java.util.List;

public interface LoanService {
    List<Loan> getLoanByPersonId(Long i);

    List<Loan> getLoans(Page page);
}
