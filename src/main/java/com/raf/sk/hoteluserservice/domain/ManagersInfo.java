package com.raf.sk.hoteluserservice.domain;


import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class ManagersInfo{
    private String hotelName;
    @Temporal(TemporalType.DATE)
    private Date startDate;

    public ManagersInfo() {
    }

    public ManagersInfo(String hotelName, Date startDate) {
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
