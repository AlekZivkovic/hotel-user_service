package com.raf.sk.hoteluserservice.dto;

public class ManagerDto extends  UserDto {
    private String hotelName;

    public ManagerDto() {
        super();
    }

    public ManagerDto(String hotelName) {
        this.hotelName = hotelName;
    }

    public ManagerDto(Long id, String email, String firstName, String lastName, String username, String hotelName) {
        super(id, email, firstName, lastName, username);
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
