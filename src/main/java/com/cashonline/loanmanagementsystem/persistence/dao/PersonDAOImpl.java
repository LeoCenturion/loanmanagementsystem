package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.Loan;
import com.cashonline.loanmanagementsystem.model.Person;
import com.cashonline.loanmanagementsystem.model.service.LoanServiceImpl;
import com.cashonline.loanmanagementsystem.persistence.entities.LoanEntity;
import com.cashonline.loanmanagementsystem.persistence.entities.PersonEntity;
import com.jasongoodwin.monads.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@Qualifier("PersonDAO")
public class PersonDAOImpl implements PersonDAO {

    private final PersonRepository personRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    public PersonDAOImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> findPerson(long id) {
        return Optional.empty();
    }

    @Override
    public Try savePerson(Person p) {
        return null;
    }

    @Override
    public void deletePerson(long l) {

    }

    @Override
    public void updatePerson(Person p) {

    }

    @Override
    @Transactional
    public LoanServiceImpl.PagedLoans getLoansPaged(Long borrowerId, LoanServiceImpl.Page page) {
        Optional<PersonEntity> pagedAnswer = personRepository.findById(borrowerId);
        List<Loan> loans = getLoansList(page, pagedAnswer);

        return new LoanServiceImpl.PagedLoans(loans, (int) getTotalPages(page, pagedAnswer));
    }



    @org.jetbrains.annotations.NotNull
    private static List<Loan> getLoansList(LoanServiceImpl.Page page, Optional<PersonEntity> pagedAnswer) {
        Integer skip = page.pageSize()* page.pageNumber();
        List<Loan> loans = pagedAnswer.stream()
                .flatMap(PersonEntity::getLoans)
                .skip(skip).limit(page.pageSize())
                .map(LoanEntity::toLoan)
                .collect(toList());
        return loans;
    }

    private static long getTotalPages(LoanServiceImpl.Page page, Optional<PersonEntity> pagedAnswer) {
        long elements = pagedAnswer.map(PersonEntity::getLoans).stream().count();
        long totalPages = elements / page.pageSize();
        return totalPages;
    }
}
