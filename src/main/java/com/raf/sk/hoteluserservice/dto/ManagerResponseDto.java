package com.raf.sk.hoteluserservice.dto;

import java.util.List;

public class ManagerResponseDto {
    List<UserDto> managers;


    public List<UserDto> getManagers() {
        return managers;
    }

    public void setManagers(List<UserDto> managers) {
        this.managers = managers;
    }

    public ManagerResponseDto() {
    }

    public ManagerResponseDto(List<UserDto> managers) {
        this.managers = managers;
    }
}
