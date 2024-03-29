package com.example.security.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  implements Serializable {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName ="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String Password;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(name="USER_ROLE",
            joinColumns=
            @JoinColumn(name="USER_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="ROLE_ID", referencedColumnName="ID")
    )
   // @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<Role> Roles = new HashSet<>();






}