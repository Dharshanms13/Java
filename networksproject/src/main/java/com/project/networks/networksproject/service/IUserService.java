package com.project.networks.networksproject.service;

import java.util.List;
import com.project.networks.networksproject.dto.UserDto;
import com.project.networks.networksproject.dto.UserInfo;

public interface IUserService {

    void createUser(UserDto user);

    List<UserInfo> getAllUsers();

    UserInfo getUserById(long id);

    UserInfo deleteUserById(long id);

    UserInfo login(String email, String password);

}
