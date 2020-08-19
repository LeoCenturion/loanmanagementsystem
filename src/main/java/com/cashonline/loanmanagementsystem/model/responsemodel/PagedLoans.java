package com.cashonline.loanmanagementsystem.model.responsemodel;

import com.cashonline.loanmanagementsystem.model.entities.Loan;

import java.util.List;

public class PagedLoans {
    private List<Loan> items;

    public Paging getPage() {
        return page;
    }

    private Paging page;

    public PagedLoans(List<Loan> items, Integer totalPages, Integer pageNumber, Integer pageSize) {
        this.items = items;
        this.page = new Paging(totalPages, pageNumber, pageSize);
    }

    public List<Loan> getItems() {
        return items;
    }

    public Integer getTotalPages() {
        return page.getTotalPages();
    }

    public Integer getPageNumber() {
        return page.getPageNumber();
    }

    public Integer getPageSize() {
        return page.getPageSize();
    }

    public void setItems(List<Loan> items) {
        this.items = items;
    }

    public void setPage(Paging page) {
        this.page = page;
    }

    private static class Paging {
        public Paging() {  }

        public Integer getTotalPages() {
            return totalPages;
        }

        public Integer getPageNumber() {
            return pageNumber;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        private Integer totalPages;
        private Integer pageNumber;
        private Integer pageSize;

        Paging(Integer totalPages, Integer pageNumber, Integer pageSize) {
            this.totalPages = totalPages;
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public void setPageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }

}
