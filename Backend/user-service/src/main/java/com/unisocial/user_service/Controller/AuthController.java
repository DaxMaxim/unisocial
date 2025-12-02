package com.unisocial.user_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisocial.user_service.Service.AuthService;
import com.unisocial.user_service.dto.AuthResponse;
import com.unisocial.user_service.dto.LoginRequest;
import com.unisocial.user_service.dto.RegisterRequest;
import com.unisocial.user_service.dto.UniRequest;
import com.unisocial.user_service.dto.UniResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UniResponse<AuthResponse>> register(
            @RequestBody UniRequest<RegisterRequest> request) {

        String token = service.register(request.getData());

        if (token == null) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("USER_EXISTS", "Email already registered"));
        }

        return ResponseEntity.ok(
                UniResponse.success(new AuthResponse(token), "Registration successful")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UniResponse<AuthResponse>> login(
            @RequestBody UniRequest<LoginRequest> request) {

        String token = service.login(request.getData());

        if (token == null) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("INVALID_CREDENTIALS", "Invalid email or password"));
        }

        return ResponseEntity.ok(
                UniResponse.success(new AuthResponse(token), "Login successful")
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<UniResponse<Boolean>> validate(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        boolean valid = service.validateToken(token);

        return ResponseEntity.ok(
                UniResponse.success(valid, valid ? "Token valid" : "Token invalid")
        );
    }
}
