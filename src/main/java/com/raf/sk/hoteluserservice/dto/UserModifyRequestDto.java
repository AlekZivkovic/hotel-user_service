package com.raf.sk.hoteluserservice.dto;


public class UserModifyRequestDto extends  UserDto{
    private String password;
    private CredentialsDto credentialsDto;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CredentialsDto getCredentialsDto() {
        return credentialsDto;
    }

    public void setCredentialsDto(CredentialsDto credentialsDto) {
        this.credentialsDto = credentialsDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
