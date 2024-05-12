package com.project.networks.networksproject.dto;

public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String reenterpassword;
    private String phonenum;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String password, String reenterpassword,
    String phonenum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.reenterpassword = reenterpassword;
        this.phonenum = phonenum;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterpassword() {
        return reenterpassword;
    }

    public void setReenterpassword(String reenterpassword) {
        this.reenterpassword = reenterpassword;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    

}
