package com.example.banking.models;


import lombok.*;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
    @Column(name = "activate")
    private Boolean activate;
    @Column(name = "name")
    private String name;
    public String getState(){
        if(activate){ return "активен";    }
        else        { return "не активен"; }
    }
    public String action(){
        if(activate){ return "блокировать";    }
        else        { return "разблокировать"; }
    }

}
