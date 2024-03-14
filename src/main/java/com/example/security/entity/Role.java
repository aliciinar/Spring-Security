package com.example.security.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

}
