package com.web.java.project.movie.bites.secutity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private final Map<String, Long> blacklist = new ConcurrentHashMap<>();

    @Autowired
    private JwtUtil jwtUtil;

    public void blacklistToken(String token) {
        long expiration = jwtUtil.extractExpiration(token).getTime();
        blacklist.put(token, expiration);
    }

    public boolean isTokenBlacklisted(String token) {
        Long expiration = blacklist.get(token);
        if (expiration == null) return false;
        if (System.currentTimeMillis() > expiration) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}
