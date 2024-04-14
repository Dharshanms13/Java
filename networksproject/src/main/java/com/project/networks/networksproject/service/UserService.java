package com.project.networks.networksproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.networks.networksproject.dao.UserDao;
import com.project.networks.networksproject.dto.UserDto;
import com.project.networks.networksproject.dto.UserInfo;
import com.project.networks.networksproject.exception.AccountAlreadyExistsException;
import com.project.networks.networksproject.exception.LoginUnsuccessfulException;
import com.project.networks.networksproject.exception.NoUsersFound;
import com.project.networks.networksproject.exception.UserNotFoundException;
import com.project.networks.networksproject.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(UserDto user) {
        User addUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getPhonenum());
        List<User> userList = userDao.findAll();
        if (userList.size() > 0) {
            for (User userCheck : userList) {
                if (addUser.getEmail().equals(userCheck.getEmail())) {
                    throw new AccountAlreadyExistsException("Email already exists in DB " + userCheck.getEmail());
                }
            }
        }
        userDao.save(addUser);
        Optional<User> addedUser = userDao.findById(addUser.getId());
        if (addedUser.isPresent()) {
            System.out.println(addedUser);
        }

    }

    @Override
    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = new ArrayList<>();
        List<User> userList = userDao.findAll();
        if (userList.size() < 1) {
            throw new NoUsersFound("No users found");
        }
        for (User user : userList) {
            users.add(new UserInfo(user.getId(), user.getFirstName(), user.getLastName(),
                    user.getEmail(), user.getPhonenum()));
        }
        return users;
    }

    @Override
    public UserInfo getUserById(long id) {
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User doesn't exists with id " + id);
        }
        UserInfo userInfo = new UserInfo(user.get().getId(), user.get().getFirstName(), user.get().getLastName(),
                user.get().getEmail(), user.get().getPhonenum());
        return userInfo;
    }

    @Override
    public UserInfo deleteUserById(long id) {
        UserInfo user = getUserById(id);
        userDao.deleteById(id);
        return user;
    }

    @Override
    public UserInfo login(String email, String password) {
        Optional<User> userCheck = userDao.findUserByEmailAndPass(email, password);
        if (!userCheck.isPresent()) {
            throw new LoginUnsuccessfulException("Email/Password doesn't match");
        }
        UserInfo user = new UserInfo(userCheck.get().getId(), userCheck.get().getFirstName(),
                userCheck.get().getLastName(), userCheck.get().getEmail(), userCheck.get().getPhonenum());

        return user;
    }

}
