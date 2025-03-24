package com.example.demo.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "role")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence_role")
    @SequenceGenerator(name = "sequence_role", sequenceName = "sequence_role", allocationSize = 1)
    Long id;

    @Column(name = "name", nullable=false)
    String name;

    @Column(name = "description")
    String description;

    @Builder.Default
    @OneToMany(mappedBy = "role")
    Set<UserToRole> roleRelationToUser = new HashSet<>();
}
