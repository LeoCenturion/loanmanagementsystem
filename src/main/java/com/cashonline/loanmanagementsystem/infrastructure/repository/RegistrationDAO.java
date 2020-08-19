package com.cashonline.loanmanagementsystem.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("RegistrationDAO")
public class RegistrationDAO {

    private final RegistrationRepository  repository;

    @Autowired
    public RegistrationDAO(RegistrationRepository repository) {
        this.repository = repository;
    }

    public Optional<Registration> findByUsername(String username) {
        return repository.findById(username);
    }

}
