package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.Customer;
import com.example.demo.jwtFilters.JwtUtils;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.SecurityService;

@RestController
public class AuthController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private SecurityService customerService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/token")
    public String testingToken() {
        return "Welcome to Dashboard";
    }
    
    @PostMapping("/subs")
    public ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        String email = authenticationRequest.getEmail();
        
        // Check if username already exists
        Optional<Customer> existingCustomer = customerRepository.findByUsername(username);
        if (existingCustomer.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Create new customer with encoded password
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));  // Encrypt password before saving
        customer.setEmail(email);
        
        try {
            customerRepository.save(customer);  // Save the customer
            return ResponseEntity.status(201).body("Successfully subscribed " + username);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during subscription: " + e.getMessage());
        }
    }
    
    @PostMapping("/auth")
    public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        
        // Authenticate the user
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: Invalid username or password");
        }
        
        // Load user and generate JWT token
        UserDetails loadedUser = customerService.loadUserByUsername(username);
        String generatedToken = jwtUtils.generateToken(loadedUser);
        
        return ResponseEntity.ok(generatedToken);
    }
}
