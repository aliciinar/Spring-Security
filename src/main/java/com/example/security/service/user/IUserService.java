package com.example.security.service.user;

import com.example.security.dto.LoginDTO;
import com.example.security.entity.User;

public interface IUserService
{
    User signUp(LoginDTO user);

    User login(String email);


}
