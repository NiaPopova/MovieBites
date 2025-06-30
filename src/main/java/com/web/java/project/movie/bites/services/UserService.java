package com.web.java.project.movie.bites.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        validateEmail(user.getEmail());
        user.setPassword(encryptPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public boolean exists(User loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BCrypt.Result result = BCrypt.verifyer().verify(
                loginRequest.getPassword().toCharArray(),
                user.getPassword()
            );
            return result.verified;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER")) // ако имаш роли в UserEntity – добави ги тук
        );
    }
}
