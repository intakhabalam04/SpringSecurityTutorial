package com.intakhab.springsecuritytutorial.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Data
@Getter
@Setter

public class UserDto {

    private String fullname;
    private String className;
    private String email;
    private String username;
    private String mobile;
    private String password;
    private String gender;
    private String role;
    private String action;

    public UserDto() {
    }

    public UserDto(String fullname, String className, String email, String username, String mobile, String password, String gender, String role, String action) {
        this.fullname = fullname;
        this.className = className;
        this.email = email;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.action = action;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
