package com.cashonline.loanmanagementsystem.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegistrationDAO {
    private final RegistrationRepository  repository;

    @Autowired
    public RegistrationDAO(RegistrationRepository repository) {
        this.repository = repository;
    }


    public void save(Registration user) {
        repository.save(user);
    }


//    public Optional<Registration> findById(String id) {
//        return Optional.ofNullable(repository.findById(id));
//    }


    public Optional<Registration> findByUsername(String username) {
        return repository.findById(username);
    }


//    public Optional<Registration> findByEmail(String email) {
//        return Optional.ofNullable(repository.findByEmail(email));
//    }
//
//
//    public void saveRelation(FollowRelation followRelation) {
//        if (!findRelation(followRelation.getUserId(), followRelation.getTargetId()).isPresent()) {
//            repository.saveRelation(followRelation);
//        }
//    }
//
//
//    public Optional<FollowRelation> findRelation(String userId, String targetId) {
//        return Optional.ofNullable(repository.findRelation(userId, targetId));
//    }
//
//
//    public void removeRelation(FollowRelation followRelation) {
//        repository.deleteRelation(followRelation);
//    }
}
