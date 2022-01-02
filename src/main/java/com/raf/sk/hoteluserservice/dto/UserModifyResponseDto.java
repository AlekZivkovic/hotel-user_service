package com.raf.sk.hoteluserservice.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class UserModifyResponseDto {
    @NotNull
    private Long id;

    private Optional<@Email String>  email;
    private Optional<@NotBlank String> firstName;
    private Optional<@NotBlank String> lastName;
    private Optional<@NotBlank String> username;
    private  Optional<@NotBlank String > password;

    private Optional<CredentialsDto> credentialsDto;
    private  Optional<ClientCreateDto.ClientsInfoDto> clientsInfoDto;
    private  Optional<ManagerCreateDto.ManagersInfoDto> managersInfo;

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public Optional<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(Optional<String> firstName) {
        this.firstName = firstName;
    }

    public Optional<String> getLastName() {
        return lastName;
    }

    public void setLastName(Optional<String> lastName) {
        this.lastName = lastName;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public Optional<CredentialsDto> getCredentialsDto() {
        return credentialsDto;
    }

    public void setCredentialsDto(Optional<CredentialsDto> credentialsDto) {
        this.credentialsDto = credentialsDto;
    }

    public Optional<ClientCreateDto.ClientsInfoDto> getClientsInfoDto() {
        return clientsInfoDto;
    }

    public void setClientsInfoDto(Optional<ClientCreateDto.ClientsInfoDto> clientsInfoDto) {
        this.clientsInfoDto = clientsInfoDto;
    }

    public Optional<ManagerCreateDto.ManagersInfoDto> getManagersInfo() {
        return managersInfo;
    }

    public void setManagersInfo(Optional<ManagerCreateDto.ManagersInfoDto> managersInfo) {
        this.managersInfo = managersInfo;
    }
}
