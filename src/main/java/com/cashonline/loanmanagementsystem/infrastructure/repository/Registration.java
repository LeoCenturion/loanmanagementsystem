package com.cashonline.loanmanagementsystem.infrastructure.repository;

import javax.persistence.*;

@Entity
@PersistenceContext(name = "myDatasource")
@Table(name = "registrations", schema = "MAIN")
public class Registration {
    @Id
    private String user;
private String password;
    private Integer personId;


    public Integer getPersonId() {
        return personId;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Registration(Integer personId, String user, String password) {
        this.personId = personId;
        this.user = user;
        this.password = password;
    }

    public Registration() {

    }
}
