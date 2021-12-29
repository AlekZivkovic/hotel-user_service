package com.raf.sk.hoteluserservice.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minNumberOfReservations;
    private Integer maxNumberOfReservations;
    private Integer discount;


    public UserRating(Integer minNumberOfReservations, Integer maxNumberOfReservations, Integer discount) {
        this.minNumberOfReservations = minNumberOfReservations;
        this.maxNumberOfReservations = maxNumberOfReservations;
        this.discount = discount;
    }

    public UserRating() {
    }

    public Long getId() {
        return id;
    }

    public Integer getMinNumberOfReservations() {
        return minNumberOfReservations;
    }

    public void setMinNumberOfReservations(Integer minNumberOfReservations) {
        this.minNumberOfReservations = minNumberOfReservations;
    }

    public Integer getMaxNumberOfReservations() {
        return maxNumberOfReservations;
    }

    public void setMaxNumberOfReservations(Integer maxNumberOfReservations) {
        this.maxNumberOfReservations = maxNumberOfReservations;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
