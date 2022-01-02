package com.raf.sk.hoteluserservice.dto;


public class UserModifyRequestDto extends  UserDto{
    private String password;
    private CredentialsDto credentialsDto;
    private ClientCreateDto.ClientsInfoDto clientsInfo;
    private ManagerCreateDto.ManagersInfoDto managersInfo;

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


    public ClientCreateDto.ClientsInfoDto getClientsInfo() {
        return clientsInfo;
    }

    public void setClientsInfo(ClientCreateDto.ClientsInfoDto clientsInfo) {
        this.clientsInfo = clientsInfo;
    }

    public ManagerCreateDto.ManagersInfoDto getManagersInfo() {
        return managersInfo;
    }

    public void setManagersInfo(ManagerCreateDto.ManagersInfoDto managersInfo) {
        this.managersInfo = managersInfo;
    }
}
