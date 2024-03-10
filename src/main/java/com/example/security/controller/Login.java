package com.example.security.controller;

import com.example.security.dto.LoginDTO;
import com.example.security.security.JwtAuthFilter;
import com.example.security.security.JwtHelper;
import com.example.security.security.JwtUtil;
import com.example.security.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1/authentication")

public class Login
{
    private static final Logger logger = LoggerFactory.getLogger(Login.class);



    @Autowired
    private IUserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("getInfo")
    public ResponseEntity<String> GetInfo()
    {
        return new ResponseEntity<String>("Info",HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup( @RequestBody LoginDTO requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginDTO loginDTO)
    {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        String token = JwtHelper.generateToken(loginDTO.getEmail());
        return ResponseEntity.ok(token);
    }



}
