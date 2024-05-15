package com.project.networks.networksproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserDetailsCommon {

    @Id
    @GeneratedValue
    private long id;
    private String aadharDetails;
    private String panDetails;

    @OneToOne
    private User user;

    public UserDetailsCommon() {
    }

    
    public UserDetailsCommon(String aadharDetails, String panDetails, User user) {
        this.aadharDetails = aadharDetails;
        this.panDetails = panDetails;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAadharDetails() {
        return aadharDetails;
    }

    public void setAadharDetails(String aadharDetails) {
        this.aadharDetails = aadharDetails;
    }

    public String getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(String panDetails) {
        this.panDetails = panDetails;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetailsCommon [id=" + id + ", aadharDetails=" + aadharDetails + ", panDetails=" + panDetails + "]";
    }

}
