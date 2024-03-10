package com.example.security.service.user;


import com.example.security.dto.LoginDTO;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import com.example.security.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public User signUp(LoginDTO request) {
        String email = request.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            System.out.println("ALready a player");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println("Hashed Password: " + hashedPassword);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
       User addedUser= userRepository.save(user);
        System.out.println(addedUser.getEmail());
        return addedUser;
    }

    @Override
    public User login(String email) {
        return null;
    }
}
