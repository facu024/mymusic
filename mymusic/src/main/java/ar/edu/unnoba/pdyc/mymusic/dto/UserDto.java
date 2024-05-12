package ar.edu.unnoba.pdyc.mymusic.dto;

import java.util.List;

public class UserDto{
    private int id;
    private String email;

    public UserDto() {
    }

    public UserDto(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
