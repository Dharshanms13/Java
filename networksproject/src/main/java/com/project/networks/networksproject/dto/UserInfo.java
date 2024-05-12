package com.project.networks.networksproject.dto;

public class UserInfo {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phonenum;

    public UserInfo() {
    }

    public UserInfo(long userId, String firstName, String lastName, String email, String phonenum) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenum = phonenum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Override
    public String toString() {
        return "UserInfo [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", phonenum=" + phonenum + "]";
    }

}
