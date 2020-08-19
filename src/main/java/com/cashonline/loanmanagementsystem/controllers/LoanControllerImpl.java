package com.cashonline.loanmanagementsystem.controllers;

import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.model.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@Qualifier("LoanController")
@RestController
@RequestMapping("loans")
public class LoanControllerImpl implements LoanController{

    private static LoanService loanService;

    public LoanControllerImpl(@Qualifier("LoanService") LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedLoans> getLoans(@RequestParam("page") Integer number, @RequestParam("size") Integer size) {
        Page page = new Page(number, size);
        return ResponseEntity.ok().body(loanService.getLoans(page));
    }
}
