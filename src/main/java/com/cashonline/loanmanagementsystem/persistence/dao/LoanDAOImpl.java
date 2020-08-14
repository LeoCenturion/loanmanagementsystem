package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.service.LoanServiceImpl;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;
import static java.util.stream.Collectors.toList;


@Repository
@Qualifier("LoanDAO")
public class LoanDAOImpl implements LoanDAO {

    private final LoanRepository repository;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public LoanDAOImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoanServiceImpl.PagedLoans getPagedLoans(LoanServiceImpl.Page page) {
        Page<LoanEntity> pagedAnswer = repository.findAll(PageRequest.of(page.pageNumber(), page.pageSize()));

        List<Loan> loans = pagedAnswer.get().map(LoanEntity::toLoan).collect(toList());
        PagedListHolder<Loan> plh = new PagedListHolder<Loan>(loans);
        plh.setPageSize(page.pageSize());
        plh.setPage(page.pageNumber());

        return new LoanServiceImpl.PagedLoans(plh.getPageList(), pagedAnswer.getTotalPages());

    }



}
