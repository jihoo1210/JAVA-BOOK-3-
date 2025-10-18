package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter

@Entity
public class Coffee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public void patch(Coffee coffeeEntity) {
        if(coffeeEntity.getName() != null) this.name = coffeeEntity.name;
        if(coffeeEntity.getPrice() != null) this.price = coffeeEntity.price;
    }
}
