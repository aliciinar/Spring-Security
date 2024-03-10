package com.example.security.repository;


import com.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email = ?1")
    User findByEmailAddress(String email);

    Optional<User> findByEmail(String email);



}