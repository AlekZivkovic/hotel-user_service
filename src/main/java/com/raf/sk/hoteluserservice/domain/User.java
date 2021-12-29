package com.raf.sk.hoteluserservice.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
//https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/
@MappedSuperclass
@Table(indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email", unique = true)})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String username;
    protected String password;
    @NotNull
    protected String email;
    @Embedded
    protected   Credentials credentials;
    @ManyToOne(optional = false)
    protected Role role;

    public User() {
    }

    public User(String username, String password, String email, Credentials credentials, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.credentials = credentials;
        this.role = role;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
