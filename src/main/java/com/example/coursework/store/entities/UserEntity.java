package com.example.coursework.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(unique = true)
    String username;
    @Column(unique = true)
    String email;
    String password;
    String roles;

    @Builder.Default
    @OneToMany
    @JoinColumn(name="user_id")
    List<OrderEntity> orders = new ArrayList<>();
}

