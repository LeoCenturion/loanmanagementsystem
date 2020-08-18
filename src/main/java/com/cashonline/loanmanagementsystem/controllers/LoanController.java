package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import org.springframework.http.ResponseEntity;

public interface LoanController {
    ResponseEntity<PagedLoans> getLoans(Page page);
}
