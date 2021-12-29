package com.raf.sk.hoteluserservice.dto;

public class ClientDto extends UserDto{
    private  Integer postcard;

    public ClientDto() {
    }

    public ClientDto(Long id, String email, String firstName, String lastName, String username) {
        super(id, email, firstName, lastName, username);
    }

    public ClientDto(Long id, String email, String firstName, String lastName, String username, Integer postcard) {
        super(id, email, firstName, lastName, username);
        this.postcard = postcard;
    }

    public ClientDto(Integer postcard) {
        this.postcard = postcard;
    }

    public Integer getPostcard() {
        return postcard;
    }

    public void setPostcard(Integer postcard) {
        this.postcard = postcard;
    }
}
