package com.project.networks.networksproject.dto;

public class UserDetailsDto {

    private String aadharDetails;
    private String panDetails;

    public UserDetailsDto() {
    }

    public UserDetailsDto(String aadharDetails, String panDetails) {
        this.aadharDetails = aadharDetails;
        this.panDetails = panDetails;
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

    @Override
    public String toString() {
        return "UserDetailsDto [aadharDetails=" + aadharDetails + ", panDetails=" + panDetails + "]";
    }

}
