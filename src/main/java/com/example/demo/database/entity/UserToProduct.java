package com.example.demo.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "user_to_product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence_user_to_products")
    @SequenceGenerator(name = "sequence_user_to_products", sequenceName = "sequence_user_to_products", allocationSize = 1)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "amount")
    Long amount; // количество купленных товаров
}
