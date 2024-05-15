package com.project.networks.networksproject.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.networks.networksproject.dto.UserDto;
import com.project.networks.networksproject.dto.UserInfo;
import com.project.networks.networksproject.exception.CommonUserException;
import com.project.networks.networksproject.exception.UserLoginRequiredException;
import com.project.networks.networksproject.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserDto user) throws IOException{
        userService.createUser(user);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/allUsers")
    public List<UserInfo> getallUser(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        if(session.getAttribute("type") == null){
            throw new CommonUserException("Not a root user");
        }
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/getUserById/{id}")
    public UserInfo getUserById(@PathVariable long id, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        if(session.getAttribute("type") == null){
            throw new CommonUserException("Not a root user");
        }
        UserInfo userInfo = userService.getUserById(id);
        return userInfo;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/deleteUserById/{id}")
    public UserInfo deleteUserById(@PathVariable long id, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null){
            throw new UserLoginRequiredException("Please Login to use service");
        }
        if(session.getAttribute("type") == null){
            throw new CommonUserException("Not a root user");
        }
        UserInfo user = userService.deleteUserById(id);
        return user;
    }    

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password, HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        UserInfo user = userService.login(email, password);
        if(session.getAttribute("userId") == null){
            session.setAttribute("userId", user.getUserId());
            if (user.getUserId() == 102) {
                session.setAttribute("type", "rootUser");
            }
        }else{
            return "Logout required";
        }
        return "Login successful......Welcome " + user;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null){
            Object obj = session.getAttribute("userId");
            Long userId = Long.parseLong(obj.toString());
            UserInfo user = userService.getUserById(userId);
            System.out.println("Logging off user " + user);
            session.invalidate();
            return "Session removed successfully";
        }
        return "No Active session found";
    }
}
