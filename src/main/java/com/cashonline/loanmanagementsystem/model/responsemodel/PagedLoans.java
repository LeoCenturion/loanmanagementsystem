package com.cashonline.loanmanagementsystem.model.responsemodel;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import java.util.List;

public class PagedLoans{

    private static record Paging(Integer totalPages, Integer pageNumber, Integer pageSize){}

    private final List<Loan> loanList;
    private final Paging paging;

    public PagedLoans(List<Loan> loanList, Integer totalPages, Integer pageNumber, Integer pageSize) {

        this.loanList = loanList;
        this.paging = new Paging(totalPages, pageNumber, pageSize);
    }

    public List<Loan> loanList() {
        return loanList;
    }

    public Integer totalPages() {
        return paging.totalPages();
    }

    public Integer pageNumber() {
        return paging.pageNumber();
    }

    public Integer pageSize() {
        return paging.pageSize();
    }
}
