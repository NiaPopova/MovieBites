package com.web.java.project.movie.bites.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        validateEmail(user.getEmail());
        user.setPassword(encryptPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("Username not found");
        }

    }

    public boolean exists(User loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent()) {
            return true;
        }
        return false;
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
