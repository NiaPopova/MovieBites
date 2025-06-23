package com.web.java.project.movie.bites.controller;

import com.web.java.project.movie.bites.entities.UserDto;
import com.web.java.project.movie.bites.entities.users.User;
import com.web.java.project.movie.bites.repository.UserRepository;
import com.web.java.project.movie.bites.secutity.JwtUtil;
import com.web.java.project.movie.bites.secutity.TokenBlacklistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie/bites")
public class UserController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenBlacklistService tokenBlacklistService;

    public UserController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder, UserRepository userRepository,
                          TokenBlacklistService tokenBlacklistService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        UsernamePasswordAuthenticationToken
            auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authManager.authenticate(auth);

        String token = jwtUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok().body("Bearer " + token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        userRepository.save(newUser);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

//    Register (Sign up)	Create a new user account	POST	/register or /users
//    Login	Authenticate user credentials	POST	/login
//    Logout	End user session (often via token invalidation)	POST or DELETE	/logout
//    Get User Info	Retrieve current or specific user details	GET	/users/me or /users/:id
//    Update User Info	Modify user profile details	PUT or PATCH	/users/:id or /profile
//    Delete User Account	Permanently delete a user	DELETE	/users/:id
//    Change Password	Update the user's password	POST or PUT	/change-password
//    Reset Password	Trigger password reset flow	POST	/forgot-password
}
