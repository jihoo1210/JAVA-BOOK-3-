package com.example.firstproject.repository;

import com.example.firstproject.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    @Override
    ArrayList<Pizza> findAll();
}
