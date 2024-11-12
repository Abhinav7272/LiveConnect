package com.LiveConnect.controller;

import com.LiveConnect.dto.JwtResponse;
import com.LiveConnect.dto.LoginRequest;
import com.LiveConnect.kafkaconfig.UserTrafficProducerService;
import com.LiveConnect.login.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserTrafficProducerService userTrafficProducerService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           UserTrafficProducerService userTrafficProducerService,
                           JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userTrafficProducerService = userTrafficProducerService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/test")
    public String test() {
        return "Login endpoint is working";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        // Generate JWT token
        String token = jwtTokenUtil.createToken(loginRequest.getUsername());

        // Log the login event to Kafka
        String eventMessage = "User " + loginRequest.getUsername() + " logged in at " + System.currentTimeMillis();
        userTrafficProducerService.sendUserTraffic(eventMessage);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticateUser(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
