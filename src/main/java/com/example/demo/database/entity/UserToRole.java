package com.example.demo.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;


@Entity
@Table(name = "user_to_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToRole {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence_role")
    @SequenceGenerator(name = "sequence_role", sequenceName = "sequence_role", allocationSize = 1)
    Long id;

    @OneToMany(mappedBy = "user_to_role")
    @Column(name = "role_id")
    List<Role> roleList;

    @OneToMany(mappedBy = "user_to_role")
    @Column(name = "user_id")
    List<User> userList;
}
