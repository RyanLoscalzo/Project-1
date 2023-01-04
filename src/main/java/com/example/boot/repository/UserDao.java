package com.example.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot.Models.User;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value="insert into users values (default, :username, :password)", nativeQuery = true)
    void createUser(String username, String password);
    
}
