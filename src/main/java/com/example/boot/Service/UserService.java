package com.example.boot.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.Models.User;
import com.example.boot.Models.UsernamePasswordAuthentication;
import com.example.boot.exceptions.ModelNotFound;
import com.example.boot.repository.UserDao;

@Service
public class UserService {
    @Autowired
	private UserDao userDao;

	public User getUserByUsername(UsernamePasswordAuthentication loginRequest) {
		Optional<User> possibleUser= this.userDao.findByUsername(loginRequest.getUsername());
		if(possibleUser.isPresent()){
			User u = possibleUser.get();
			if (u.getPassword().equals(loginRequest.getPassword())) {
				return u;
			} else {
				throw new ModelNotFound("Username or password is incorrect");
			}
        } else {
            throw new ModelNotFound("Username or password is incorrect");
        }
	}

	public String register(UsernamePasswordAuthentication registerRequest) {
		this.userDao.createUser(registerRequest.getUsername(), registerRequest.getPassword());
		return "User created";
	}
}
