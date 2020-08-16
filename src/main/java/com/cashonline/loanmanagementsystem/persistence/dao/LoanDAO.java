package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;
import com.jasongoodwin.monads.Try;

public interface LoanDAO {

    PagedLoans getLoansPaged(Page page);

    Try saveLoan(LoanEntity loan);
}
