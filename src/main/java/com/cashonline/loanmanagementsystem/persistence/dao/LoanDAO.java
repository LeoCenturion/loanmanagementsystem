package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.service.LoanServiceImpl;

public interface LoanDAO {

    LoanServiceImpl.PagedLoans getPagedLoans(LoanServiceImpl.Page page);

}
