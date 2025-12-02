package com.unisocial.user_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unisocial.user_service.Repository.UserRepository;
import com.unisocial.user_service.dto.LoginRequest;
import com.unisocial.user_service.dto.RegisterRequest;
import com.unisocial.user_service.entity.User;
import com.unisocial.user_service.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwt;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest req) {
        if (repo.findByEmail(req.getEmail()).isPresent()) {
            return null;
        }

        User u = new User();
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());

        repo.save(u);
        return jwt.generateToken(u.getId(), u.getEmail());
    }

    public String login(LoginRequest req) {
        User u = repo.findByEmail(req.getEmail()).orElse(null);
        if (u == null) return null;

        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            return null;
        }

        return jwt.generateToken(u.getId(), u.getEmail());
    }

    public boolean validateToken(String token) {
        try {
            jwt.validate(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
