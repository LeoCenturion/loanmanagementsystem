package com.cashonline.loanmanagementsystem.persistence;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.persistence.dao.LoanDAO;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;
import com.jasongoodwin.monads.Try;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Qualifier("fakeLoanRepository")
public class FakeLoanRepository implements LoanDAO {

    private Map<Long, Loan> loans = new HashMap<>();

    public FakeLoanRepository() {}

    public FakeLoanRepository(HashMap<Long, Loan> map) {
        this.loans = map;
    }

    @Override
    public PagedLoans getLoansPaged(Page page) {
        return new PagedLoans(new ArrayList<>(this.loans.values()), 0, page.pageNumber(), page.pageSize());
    }

    @Override
    public Try saveLoan(LoanEntity loan) {
        return null;
    }

    @Override
    public Optional<Loan> findLoan(Integer id) {
        return Optional.empty();
    }


}
