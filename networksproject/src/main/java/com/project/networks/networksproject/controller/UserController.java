package com.project.networks.networksproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.networks.networksproject.dto.UserDetailsDto;
import com.project.networks.networksproject.exception.UserLoginRequiredException;
import com.project.networks.networksproject.model.User;
import com.project.networks.networksproject.model.UserDetailsCommon;
import com.project.networks.networksproject.service.IUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private IUserDetails userDetails;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/user/addInfo/{aadharDetails}/{panDetails}")
    public void addInfo(@PathVariable String aadharDetails, @PathVariable String panDetails, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        Object obj = session.getAttribute("userId");
        Long id = Long.parseLong(obj.toString());

        userDetails.addUserDetails(aadharDetails, panDetails, id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/user/getInfo") 
    public UserDetailsDto getInfo(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        Object obj = session.getAttribute("userId");
        Long id = Long.parseLong(obj.toString());

        UserDetailsDto userDetailsDto = userDetails.getUserDetails(id);

        return userDetailsDto;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/user/updateAadharInfo/{aadharInfo}") 
    public void updateAadharInfo(@PathVariable String aadharInfo, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        Object obj = session.getAttribute("userId");
        Long id = Long.parseLong(obj.toString());

        userDetails.updateAadharInfo(id, aadharInfo);
    }


}
