package com.raf.sk.hoteluserservice.domain;


import javax.persistence.Embeddable;

@Embeddable
public class ClientsInfo {
    private Integer postcard;
    private Integer numberOfReservation;

    public ClientsInfo() {
    }

    public ClientsInfo(Integer postcard, Integer numberOfReservation) {
        this.postcard = postcard;
        this.numberOfReservation = numberOfReservation;
    }

    public Integer getPostcard() {
        return postcard;
    }

    public void setPostcard(Integer postcard) {
        this.postcard = postcard;
    }

    public Integer getNumberOfReservation() {
        return numberOfReservation;
    }

    public void setNumberOfReservation(Integer numberOfReservation) {
        this.numberOfReservation = numberOfReservation;
    }
}
