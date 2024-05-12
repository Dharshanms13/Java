package com.project.networks.networksproject.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.project.networks.networksproject.exception.ReEnterPasswordIncorrectException;
import com.project.networks.networksproject.exception.UserNotFoundException;
import com.project.networks.networksproject.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(UserDto user) throws IOException{
        User addUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getPhonenum());
        List<User> userList = userDao.findAll();

        if (userList.size() > 0) {
            List<UserInfo> userInfo = new ArrayList<UserInfo>();
            for(User users: userList) {
            userInfo.add(decryptUser(users));
            }

            for(UserInfo userInfoS: userInfo) {
                if (addUser.getEmail().equals(userInfoS.getEmail())) {
                    throw new AccountAlreadyExistsException("Email already exists in DB " +
                    userInfoS.getEmail());
                }
            }

        }

        if(!addUser.getPassword().equals(user.getReenterpassword())){
        throw new ReEnterPasswordIncorrectException("ReEnter password wrong");
        }

        // encrypt data
        
        User addEncryptedUser = encrypt(addUser);

        userDao.save(addEncryptedUser);
        // Optional<User> addedUser = userDao.findById(addUser.getId());
        // if (addedUser.isPresent()) {
        //     System.out.println(addedUser);
        // }

    }

    @Override
    public List<UserInfo> getAllUsers() throws IOException {
        List<UserInfo> users = new ArrayList<>();
        List<User> userList = userDao.findAll();
        if (userList.size() < 1) {
            throw new NoUsersFound("No users found");
        }
        for (User user : userList) {
            users.add(decryptUser(user));
        }
        return users;
    }

    @Override
    public UserInfo getUserById(long id) throws IOException{
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User doesn't exists with id " + id);
        }
        UserInfo userInfo = new UserInfo();
        userInfo = decryptUser(user.get());
        return userInfo;
    }

    @Override
    public UserInfo deleteUserById(long id) throws IOException{
        UserInfo user = getUserById(id);
        userDao.deleteById(id);
        return user;
    }

    @Override
    public UserInfo login(String email, String password) throws IOException{
        List<String> encryptData = new ArrayList<String>();
        encryptData.add(encData(email));
        encryptData.add(encData(password)); 
        Optional<User> userCheck = userDao.findUserByEmailAndPass(encryptData.get(0), encryptData.get(1));
        if (!userCheck.isPresent()) {
            throw new LoginUnsuccessfulException("Email/Password doesn't match");
        }
        UserInfo user = new UserInfo();

        user = decryptUser(userCheck.get());

        return user;
    }

    private String encData(String Data) throws IOException{
        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//encrypt.py",
                Data);

        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String lines = null;

        List<String> arr = new ArrayList<>();

        while ((lines = reader.readLine()) != null) {
            arr.add(lines);
        }

        while ((lines = errorReader.readLine()) != null) {
            System.out.println(lines);
        }

        return arr.get(0);
    }

    // encrypt data
    private User encrypt(User addUser) throws IOException{

        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//encrypt.py",
                addUser.getFirstName(), addUser.getLastName(), addUser.getEmail(), addUser.getPassword(),
                addUser.getPhonenum());

        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String lines = null;

        List<String> arr = new ArrayList<>();

        while ((lines = reader.readLine()) != null) {
            arr.add(lines);
        }

        while ((lines = errorReader.readLine()) != null) {
            System.out.println(lines);
        }

        addUser.setFirstName(arr.get(0));
        addUser.setLastName(arr.get(1));
        addUser.setEmail(arr.get(2));
        addUser.setPassword(arr.get(3));
        addUser.setPhonenum(arr.get(4));

        return addUser;
    }

    private UserInfo decryptUser(User users) throws IOException{

        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//decrypt.py",
                users.getFirstName(), users.getLastName(), users.getEmail(), users.getPassword(),
                users.getPhonenum());

        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String lines = null;

        List<String> arr = new ArrayList<>();

        while ((lines = reader.readLine()) != null) {
            arr.add(lines);
        }

        while ((lines = errorReader.readLine()) != null) {
            System.out.println(lines);
        }

        UserInfo userDetails = new UserInfo(users.getId(), arr.get(0),arr.get(1),arr.get(2),arr.get(4));

        return userDetails;
    }

}
