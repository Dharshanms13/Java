package com.project.networks.networksproject.service;

import java.io.IOException;
import java.util.List;
import com.project.networks.networksproject.dto.UserDto;
import com.project.networks.networksproject.dto.UserInfo;

public interface IUserService {

    void createUser(UserDto user) throws IOException;

    List<UserInfo> getAllUsers() throws IOException;

    UserInfo getUserById(long id) throws IOException;

    UserInfo deleteUserById(long id) throws IOException;

    UserInfo login(String email, String password) throws IOException;

}
