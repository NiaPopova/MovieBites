package com.web.java.project.movie.bites.controllers;

import com.web.java.project.movie.bites.dto.UserDto;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.mapper.UserMapper;
import com.web.java.project.movie.bites.secutity.JwtUtil;
import com.web.java.project.movie.bites.secutity.TokenBlacklistService;
import com.web.java.project.movie.bites.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie/bites")
public class UserController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.dtoToUser(userDto);
        User savedUser = userService.register(user);
        UserDto responseDto = userMapper.userToDto(savedUser);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        if (userService.exists(loginRequest)) {
            UsernamePasswordAuthenticationToken
                auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            authManager.authenticate(auth);

            String token = jwtUtil.generateToken(loginRequest.getUsername());

            return ResponseEntity.ok().body("Bearer " + token);
        } else {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("users/current_user")
    public ResponseEntity<UserDto> getCurrentUser(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(userMapper.userToDto(userService.findByUsername(username)));
    }

    @GetMapping("users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userMapper.userToDto(userService.findByUsername(username)));
    }

//    Update User Info	Modify user profile details	PUT or PATCH	/users/:id or /profile
//    Delete User Account	Permanently delete a user	DELETE	/users/:id
//    Change Password	Update the user's password	POST or PUT	/change-password
//    Reset Password	Trigger password reset flow	POST	/forgot-password
}
