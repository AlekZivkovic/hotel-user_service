package com.raf.sk.hoteluserservice.dto;

public class NotificationTypeDto {
    private String type;
    private String pattern;




    public NotificationTypeDto(String type, String pattern) {
        this.type = type;
        this.pattern = pattern;
    }

    public NotificationTypeDto() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public NotificationTypeDto(String msg) {
        this.pattern = msg;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
