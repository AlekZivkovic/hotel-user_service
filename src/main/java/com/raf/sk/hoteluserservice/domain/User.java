package com.raf.sk.hoteluserservice.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
//https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/
@Inheritance(strategy =InheritanceType.JOINED)
@Table(indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email", unique = true)})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @NotNull
    private String email;
    @Embedded
    private Credentials credentials;
    @Embedded
    private ClientsInfo clientsInfo;
    @Embedded
    private ManagersInfo managersInfo;
    @ManyToOne(optional = false)
    private Role role;




    public User() {
    }

    public User(String username, String password, String email, Credentials credentials,
                Role role, ClientsInfo clientsInfo, ManagersInfo managersInfo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.credentials = credentials;
        this.role = role;
        this.clientsInfo = clientsInfo;
        this.managersInfo = managersInfo;
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

    public ClientsInfo getClientsInfo() {
        return clientsInfo;
    }

    public void setClientsInfo(ClientsInfo clientsInfo) {
        this.clientsInfo = clientsInfo;
    }

    public ManagersInfo getManagersInfo() {
        return managersInfo;
    }

    public void setManagersInfo(ManagersInfo managersInfo) {
        this.managersInfo = managersInfo;
    }
}
