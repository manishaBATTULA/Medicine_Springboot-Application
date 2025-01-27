package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class SecurityService implements UserDetailsService {
	
	@Autowired 
	private CustomerRepository customerRepository;

	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the customer from the repository
        Optional<Customer> foundedUser = customerRepository.findByUsername(username);

        // Check if the customer exists
        if (!foundedUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Get the customer details from the Optional
        Customer customer = foundedUser.get();
        String name = customer.getUsername();
        String password = customer.getPassword();
        String email = customer.getEmail(); // You can use this if needed in UserDetails

        // Return a UserDetails object (Spring Security User)
        return new User(name, password, new ArrayList<>()); // You can add authorities if needed
    }
}