package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    // SELECT
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // CRETE
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto) {
        Coffee target = dto.toEntity();
        log.info("dto: {}, entity: {}", dto, target);
        return coffeeRepository.save(target);
    }

    // UPDATE
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
        Coffee coffeeEntity = dto.toEntity();
        log.info("dto: {}, entity: {}", dto, coffeeEntity);

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != target.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        target.patch(coffeeEntity);
        coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
