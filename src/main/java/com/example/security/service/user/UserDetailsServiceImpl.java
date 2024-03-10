package com.example.security.service.user;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}