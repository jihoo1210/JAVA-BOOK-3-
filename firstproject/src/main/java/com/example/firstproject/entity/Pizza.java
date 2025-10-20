package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pizza {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public PizzaDto createPizzaDto() {
        return new PizzaDto(this.id, this.getName(), this.getPrice());
    }

    public void patch(PizzaDto dto) {
        if(dto.getName() != null) this.name = dto.getName();
        if(dto.getPrice() != null) this.price = dto.getPrice();
    }
}
