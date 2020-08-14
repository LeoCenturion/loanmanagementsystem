package com.cashonline.loanmanagementsystem.persistence.dao;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import com.jasongoodwin.monads.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import com.cashonline.loanmanagementsystem.persistence.entities.*;


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
    public PagedLoans getLoansPaged(Long borrowerId, Page page) {
        Optional<PersonEntity> pagedAnswer = personRepository.findById(borrowerId);
        List<Loan> loans = getLoansList(page, pagedAnswer);

        return new PagedLoans(loans, (int) getTotalPages(page, pagedAnswer));
    }



    @org.jetbrains.annotations.NotNull
    private static List<Loan> getLoansList(Page page, Optional<PersonEntity> pagedAnswer) {
        Integer skip = page.pageSize()* page.pageNumber();
        List<Loan> loans = pagedAnswer.stream()
                .flatMap(PersonEntity::getLoans)
                .skip(skip).limit(page.pageSize())
                .map(LoanEntity::toLoan)
                .collect(toList());
        return loans;
    }

    private static long getTotalPages(Page page, Optional<PersonEntity> pagedAnswer) {
        long elements = pagedAnswer.map(PersonEntity::getLoans).stream().count();
        long totalPages = elements / page.pageSize();
        return totalPages;
    }
}
