package com.sd.assignment1.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@ToString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(nullable = false, unique = true)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "role")
    private String role;
    @Column(name = "password")
    private String password;

}
