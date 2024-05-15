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
import com.project.networks.networksproject.dao.UserDetailsDao;
import com.project.networks.networksproject.dto.UserDetailsDto;
import com.project.networks.networksproject.dto.UserInfo;
import com.project.networks.networksproject.exception.UserDetailsException;
import com.project.networks.networksproject.model.User;
import com.project.networks.networksproject.model.UserDetailsCommon;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetails implements IUserDetails {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userDao.findById(id);
        return user.get();
    }

    @Override
    public void addUserDetails(String aadharDetails, String panDetails, Long id) throws IOException {
        User user = getUser(id);
        UserDetailsCommon userDetailsCommonFound = userDetailsDao.findUserByUserId(user);
        if (userDetailsCommonFound == null) {
            UserDetailsCommon userDetailsCommon = new UserDetailsCommon(aadharDetails, panDetails, user);
            encrypt(userDetailsCommon);
            userDetailsDao.save(userDetailsCommon);
        }else{
            throw new UserDetailsException("Details already added");
        }

    }

    @Override
    public UserDetailsDto getUserDetails(Long id) throws IOException {

        User user = getUser(id);
        UserDetailsCommon userDetailsCommon1 = userDetailsDao.findUserByUserId(user);
        if (userDetailsCommon1 == null) {
            throw new UserDetailsException("Please add your details to view it.");
        }
        decrypt(userDetailsCommon1);

        UserDetailsDto userDetailsDto = new UserDetailsDto(userDetailsCommon1.getAadharDetails(),
        userDetailsCommon1.getPanDetails());
        encrypt(userDetailsCommon1);
        return userDetailsDto;
    }

    @Override
    public void updateAadharInfo(Long id, String aadharInfo) throws IOException{
        User user = getUser(id);
        UserDetailsCommon userDetailsCommon2 = userDetailsDao.findUserByUserId(user);
        if (userDetailsCommon2 == null) {
            throw new UserDetailsException("Please add your details to update it.");
        }

        String aD = encryptData(aadharInfo);
        userDetailsCommon2.setAadharDetails(aD);
        userDetailsDao.save(userDetailsCommon2);
    }

    private UserDetailsCommon encrypt(UserDetailsCommon userDetailsCommon) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//encrypt.py",
                userDetailsCommon.getAadharDetails(), userDetailsCommon.getPanDetails());

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

        userDetailsCommon.setAadharDetails(arr.get(0));
        userDetailsCommon.setPanDetails(arr.get(1));

        return userDetailsCommon;
    }

    private UserDetailsCommon decrypt(UserDetailsCommon userDetailsCommon1) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//decrypt.py",
                userDetailsCommon1.getAadharDetails(), userDetailsCommon1.getPanDetails());

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

        userDetailsCommon1.setAadharDetails(arr.get(0));
        userDetailsCommon1.setPanDetails(arr.get(1));

        return userDetailsCommon1;
    }

    private String encryptData(String data) throws IOException{
        ProcessBuilder pb = new ProcessBuilder("python",
                "D://NewWorkspace//Start//Java//networksproject//src//main//java//com//project//networks//networksproject//service//encrypt.py",
                data);

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
}
