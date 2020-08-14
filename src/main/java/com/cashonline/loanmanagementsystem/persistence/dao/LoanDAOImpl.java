package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

import static com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity.GET_ALL_LOANS;
import static java.util.stream.Collectors.toList;


@Repository
@Qualifier("LoanRepository")

public class LoanDAOImpl  implements LoanDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Loan> getLoans() {
        Query query = em.createNamedQuery(GET_ALL_LOANS, LoanEntity.class);
        List<LoanEntity> loans =  query.getResultList();
        return loans.stream().map(LoanEntity::toLoan).collect(toList());
    }
}
