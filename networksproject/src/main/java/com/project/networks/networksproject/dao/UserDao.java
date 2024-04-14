package com.project.networks.networksproject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.networks.networksproject.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

    @Query("from User where email=:email and password=:password")
    Optional<User> findUserByEmailAndPass(@Param("email") String email, @Param("password") String password);

}
