package com.example.security.service.user;


import com.example.security.dto.LoginDTO;
import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.security.JwtHelper;
import com.example.security.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)

public class UserService  implements  IUserService
{
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public User signUp(LoginDTO request) {
        String email = request.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            System.out.println("ALready a player");
            return null;
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println("Hashed Password: " + hashedPassword);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        Role role = roleRepository.findByName("role_user").get();
        user.getRoles().add(role);
       User addedUser= userRepository.save(user);
        System.out.println(addedUser.getEmail());
        return addedUser;
    }

    @Override
    public String login(LoginDTO loginDTO)
    {
        Optional<User> existingUser = userRepository.findByEmail(loginDTO.getEmail());
        if(!existingUser.isPresent())
        {
            throw new RuntimeException("Not existing player");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        String token = JwtHelper.generateToken(loginDTO.getEmail());
        return token;
    }

}
