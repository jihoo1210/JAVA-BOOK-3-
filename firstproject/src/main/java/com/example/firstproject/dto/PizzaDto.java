package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private String price;

    public Pizza toEntity() {
        return new Pizza(this.id, this.name, this.price);
    }
}
