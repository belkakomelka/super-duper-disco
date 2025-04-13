package com.example.demo.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_users")
    @SequenceGenerator(
            name = "sequence_users",
            sequenceName = "sequence_users",
            allocationSize = 1
    )
    Long id;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "user_salt", nullable = false)
    String userSalt;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    Set<UserToRole> userRelationToRole = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    Set<UserToProduct> userRelationToProduct = new HashSet<>();
}
