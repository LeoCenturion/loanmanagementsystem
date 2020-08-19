package com.cashonline.loanmanagementsystem.model.requestmodel;

import java.util.Objects;

public class Page{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(pageNumber, page.pageNumber) &&
                Objects.equals(pageSize, page.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize);
    }

    public Integer pageNumber() {
        return pageNumber;
    }

    public Integer pageSize() {
        return pageSize;
    }

    private final Integer pageNumber;
    private final Integer pageSize;

    public Page(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
