package com.example.security.service.user;

import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository repository;



    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = null;
        try {
            user = repository.findByEmail(email).orElseThrow(() ->
                    new Exception("asd"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("user Password loadbyusername: " + user.getPassword());
        Set<String> roleStrings = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        System.out.println("Role: " + roleStrings.toArray(new String[0]));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roleStrings.toArray(new String[0]))
                .build();
    }
}