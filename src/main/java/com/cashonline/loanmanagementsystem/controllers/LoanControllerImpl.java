package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.model.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Qualifier("LoanController")
public class LoanControllerImpl implements LoanController{

    private static LoanService loanService;

    public LoanControllerImpl(@Qualifier("LoanService") LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public ResponseEntity<PagedLoans> getLoans(Page page) {
        return ResponseEntity.ok().body(loanService.getLoans(page));
    }
}
