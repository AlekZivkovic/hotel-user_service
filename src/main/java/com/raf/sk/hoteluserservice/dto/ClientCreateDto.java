package com.raf.sk.hoteluserservice.dto;

import com.raf.sk.hoteluserservice.domain.ClientsInfo;
import org.hibernate.validator.constraints.Length;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClientCreateDto {

    @Email
    private String email;
    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    @NotNull
    private ClientsInfoDto clientsInfoDto;
    @NotNull
    private  CredentialsDto credentialsDto;



    public static class ClientsInfoDto{
        @NotBlank
        private Integer postcard;

        public Integer getPostcard() {
            return postcard;
        }

        public void setPostcard(Integer postcard) {
            this.postcard = postcard;
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

    public ClientsInfoDto getClientsInfoDto() {
        return clientsInfoDto;
    }

    public void setClientsInfoDto(ClientsInfoDto clientsInfoDto) {
        this.clientsInfoDto = clientsInfoDto;
    }

    public CredentialsDto getCredentialsDto() {
        return credentialsDto;
    }

    public void setCredentialsDto(CredentialsDto credentialsDto) {
        this.credentialsDto = credentialsDto;
    }
}
