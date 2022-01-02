package com.raf.sk.hoteluserservice.dto;

public class NotificationServiceRequestManagerDto {
    private  ManagerCreateDto managerCreateDto;

    public NotificationServiceRequestManagerDto() {
    }

    public ManagerCreateDto getManagerCreateDto() {
        return managerCreateDto;
    }

    public void setManagerCreateDto(ManagerCreateDto managerCreateDto) {
        this.managerCreateDto = managerCreateDto;
    }
}
