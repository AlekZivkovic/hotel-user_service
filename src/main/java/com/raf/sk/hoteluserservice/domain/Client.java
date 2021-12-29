package com.raf.sk.hoteluserservice.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Client extends  User{
    private Integer numberOfPostcard;
    private Integer numberOfReservation;

    public Client() {

    }

    public Client(Integer numberOfPostcard, Integer numberOfReservation) {
        this.numberOfPostcard = numberOfPostcard;
        this.numberOfReservation = numberOfReservation;
    }

    public Client(String username, String password, String email, Credentials credentials, Role role, Integer numberOfPostcard, Integer numberOfReservation) {
        super(username, password, email, credentials, role);
        this.numberOfPostcard = numberOfPostcard;
        this.numberOfReservation = numberOfReservation;
    }

    public Integer getNumberOfPostcard() {
        return numberOfPostcard;
    }

    public void setNumberOfPostcard(Integer numberOfPostcard) {
        this.numberOfPostcard = numberOfPostcard;
    }

    public Integer getNumberOfReservation() {
        return numberOfReservation;
    }

    public void setNumberOfReservation(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }
}
