package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.Loan;
import java.util.List;

public interface LoanService {
    List<Loan> getLoanByPersonId(Long i);

    List<Loan> getLoans();
}
