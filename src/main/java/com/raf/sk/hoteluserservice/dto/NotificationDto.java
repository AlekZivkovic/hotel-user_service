package com.raf.sk.hoteluserservice.dto;

import java.time.Instant;
import java.util.Optional;

public class NotificationDto {

    private UserDto userDto;
    private NotificationTypeDto  notificationType;

    private  String newPassword;
    private String verifyLink;
    private ReservationDto reservationDto;
    private  Instant sentTime;

    public NotificationDto() {
    }


    public Instant getSentTime() {
        return sentTime;
    }

    public void setSentTime(Instant sentTime) {
        this.sentTime = sentTime;
    }

    public ReservationDto getReservationDto() {
        return reservationDto;
    }

    public NotificationTypeDto getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationTypeDto notificationType) {
        this.notificationType = notificationType;
    }

    public void setReservationDto(ReservationDto reservationDto) {
        this.reservationDto = reservationDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }



    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerifyLink() {
        return verifyLink;
    }

    public void setVerifyLink(String verifyLink) {
        this.verifyLink = verifyLink;
    }

}
