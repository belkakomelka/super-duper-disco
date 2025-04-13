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
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence_user_to_role")
    @SequenceGenerator(name = "sequence_user_to_role", sequenceName = "sequence_user_to_role", allocationSize = 1)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
}
