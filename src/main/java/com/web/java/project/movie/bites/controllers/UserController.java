package com.web.java.project.movie.bites.controllers;

import com.web.java.project.movie.bites.dto.UserDto;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.mapper.UserMapper;
import com.web.java.project.movie.bites.secutity.JwtUtil;
import com.web.java.project.movie.bites.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie/bites")
public class UserController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(userMapper.userToDto(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Successfully logged out. Please delete token on client side.");
    }

    @GetMapping("/users/current_user")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userMapper.userToDto(user));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(userMapper.userToDto(user));
    }
}
