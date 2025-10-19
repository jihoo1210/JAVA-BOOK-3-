package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    // SELECT
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // CREATE
    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        log.info("dto: {}, coffee: {}", dto, coffee);
        if(coffee.getId() != null) return null;
        return coffeeRepository.save(coffee);
    }

    // UPDATE
    public Coffee update(Long id, CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        log.info("coffee: {}, target: {}", coffee, target);
        if(target == null || coffee.getId() != id) return null;
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    // DELETE
    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null) return null;
        coffeeRepository.delete(target);
        return target;
    }

}
