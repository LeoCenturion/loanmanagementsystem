package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

import static com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity.GET_ALL_LOANS;
import static java.util.stream.Collectors.toList;


@Repository
@Qualifier("LoanRepository")

public class LoanDAOImpl implements LoanDAO {


    private final LoanRepository repository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    public LoanDAOImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Loan> getLoans(Page page) {
        PagedListHolder<Loan> plh = new PagedListHolder(repository.findAll(PageRequest.of(page.pageNumber(), page.pageSize()))
                .get().map(LoanEntity::toLoan).collect(toList()));
        plh.setPageSize(2);
        plh.setPage(0);
        return plh.getPageList();
    }
}
