package com.apps.basichttpauthentication.dto;

import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    @NotNull(message = "Username field is required!")
    private String username;

    @NotNull(message = "Password field is required!")
    private String password;

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
}
