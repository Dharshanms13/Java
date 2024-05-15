package com.project.networks.networksproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.networks.networksproject.model.User;
import com.project.networks.networksproject.model.UserDetailsCommon;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetailsCommon, Long>{

    @Query("from UserDetailsCommon where user=:user")
    UserDetailsCommon findUserByUserId(@Param("user") User user);

}
