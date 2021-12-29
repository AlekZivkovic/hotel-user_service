package com.raf.sk.hoteluserservice.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("M")
public class Manager extends  User {
    private String hotelName;
    private Date startDate;

    public Manager() {
    }

    public Manager(String hotelName, Date startDate) {
        this.hotelName = hotelName;
        this.startDate = startDate;
    }

    public Manager(String username, String password, String email, Credentials credentials, Role role, String hotelName, Date startDate) {
        super(username, password, email, credentials, role);
        this.hotelName = hotelName;
        this.startDate = startDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
