package com.raf.sk.hoteluserservice.dto;

public class UserModifyResponseDto {
    private Long id;

    private String  email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private CredentialsDto credentialsDto;
    private  ClientCreateDto.ClientsInfoDto clientsInfoDto;
    private  ManagerCreateDto.ManagersInfoDto managersInfo;


    public UserModifyResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public CredentialsDto getCredentialsDto() {
        return credentialsDto;
    }

    public void setCredentialsDto(CredentialsDto credentialsDto) {
        this.credentialsDto = credentialsDto;
    }

    public ClientCreateDto.ClientsInfoDto getClientsInfoDto() {
        return clientsInfoDto;
    }

    public void setClientsInfoDto(ClientCreateDto.ClientsInfoDto clientsInfoDto) {
        this.clientsInfoDto = clientsInfoDto;
    }

    public ManagerCreateDto.ManagersInfoDto getManagersInfo() {
        return managersInfo;
    }

    public void setManagersInfo(ManagerCreateDto.ManagersInfoDto managersInfo) {
        this.managersInfo = managersInfo;
    }
}
