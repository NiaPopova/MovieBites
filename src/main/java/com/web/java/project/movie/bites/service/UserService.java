package com.web.java.project.movie.bites.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        validateEmail(user.getEmail());
        user.setPassword(encryptPassword(user.getPassword()));

        return userRepository.save(user);
    }

    private void validateEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("You must enter a valid email address!");
        }
    }

    private String encryptPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
