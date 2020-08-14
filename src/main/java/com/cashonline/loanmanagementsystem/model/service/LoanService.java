package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;

import java.util.List;

public interface LoanService {
    List<Loan> getLoanByPersonId(Long i);

    PagedLoans getLoans(Page page);
}
