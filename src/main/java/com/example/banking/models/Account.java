package com.example.banking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
