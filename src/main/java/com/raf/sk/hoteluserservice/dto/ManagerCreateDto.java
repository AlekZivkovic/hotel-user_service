package com.raf.sk.hoteluserservice.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ManagerCreateDto {
    @Email
    private String email;
    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    @NotNull
    private ManagersInfoDto managersInfoDto;
    @NotNull
    private CredentialsDto credentialsDto;




    public static class ManagersInfoDto{
        @NotBlank
        private String hotelName;
        @NotNull
        private Date startDate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ManagersInfoDto getManagersInfoDto() {
        return managersInfoDto;
    }

    public void setManagersInfoDto(ManagersInfoDto managersInfoDto) {
        this.managersInfoDto = managersInfoDto;
    }

    public CredentialsDto getCredentialsDto() {
        return credentialsDto;
    }

    public void setCredentialsDto(CredentialsDto credentialsDto) {
        this.credentialsDto = credentialsDto;
    }
}
