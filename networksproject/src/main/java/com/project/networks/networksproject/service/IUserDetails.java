package com.project.networks.networksproject.service;

import java.io.IOException;

import com.project.networks.networksproject.dto.UserDetailsDto;
import com.project.networks.networksproject.model.User;
import com.project.networks.networksproject.model.UserDetailsCommon;

public interface IUserDetails {

    User getUser(Long id);

    void addUserDetails(String aadharDetails, String panDetails, Long id) throws IOException;

    UserDetailsDto getUserDetails(Long id) throws IOException;

    void updateAadharInfo(Long id, String aadharInfo) throws IOException;

}
