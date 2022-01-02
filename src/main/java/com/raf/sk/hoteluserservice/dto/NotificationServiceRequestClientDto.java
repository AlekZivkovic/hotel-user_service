package com.raf.sk.hoteluserservice.dto;

public class NotificationServiceRequestClientDto {
    private  ClientCreateDto clientCreateDto;

    public NotificationServiceRequestClientDto() {
    }

    public ClientCreateDto getClientCreateDto() {
        return clientCreateDto;
    }

    public void setClientCreateDto(ClientCreateDto clientCreateDto) {
        this.clientCreateDto = clientCreateDto;
    }
}
