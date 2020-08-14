package com.cashonline.loanmanagementsystem.persistence;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.service.LoanServiceImpl;
import com.cashonline.loanmanagementsystem.persistence.dao.LoanDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeLoanRepository")
public class FakeLoanRepository implements LoanDAO {

    private Map<Long, Loan> loans = new HashMap<>();


    public FakeLoanRepository() {

    }

    public FakeLoanRepository(HashMap<Long, Loan> map) {
        this.loans = map;
    }

    @Override
    public LoanServiceImpl.PagedLoans getPagedLoans(LoanServiceImpl.Page page) {
        return new LoanServiceImpl.PagedLoans(new ArrayList<>(this.loans.values()), 0);
    }


}
