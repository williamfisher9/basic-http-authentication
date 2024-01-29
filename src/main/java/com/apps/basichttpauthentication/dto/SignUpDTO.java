package com.apps.basichttpauthentication.dto;

import com.apps.basichttpauthentication.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class SignUpDTO {
    @NotNull(message = "Username field is required!")
    private String username;

    @NotNull(message = "Password field is required!")
    private String password;

    @NotNull(message = "Email field is required!")
    @Email
    private String email;

    @NotNull(message = "Full name field is required!")
    private String fullName;

    @NotNull(message = "Roles field is required!")
    private Set<String> roles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
