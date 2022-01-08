package com.raf.sk.hoteluserservice.dto;

import java.time.Instant;

public class ReservationDto {
    private Long reservationId;
    private Instant reservationTime;
    private String hotelName;

    public ReservationDto() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Instant getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Instant reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reservationId=" + reservationId +
                ", reservationTime=" + reservationTime +
                ", hotelName='" + hotelName + '\'' +
                '}';
    }
}
