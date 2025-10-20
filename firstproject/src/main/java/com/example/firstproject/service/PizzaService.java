package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDto> index() {
        return pizzaRepository.findAll().stream().map(Pizza::createPizzaDto).toList();
    }

    public PizzaDto show(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("피자 검색 실패! 존재하지 않는 아이디입니다."));
        return pizza.createPizzaDto();
    }

    public PizzaDto create(PizzaDto dto) {
        if(dto.getId() != null) throw new IllegalArgumentException("피자 생성 실패! 아이디를 입력을 하면 안됩니다.");
        Pizza target = dto.toEntity();
        Pizza created = pizzaRepository.save(target);
        return created.createPizzaDto();
    }

    public PizzaDto update(Long id, PizzaDto dto) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("피자 업데이트 실패! 존재하지 않는 아이디입니다."));
        if(dto.getId() != id) throw new IllegalArgumentException("피자 업데이트 실패! 아이디가 일치하지 않습니다.");

        target.patch(dto);
        Pizza updated = pizzaRepository.save(target);
        return updated.createPizzaDto();
    }

    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("피자 삭제 실패! 존재하지 않는 아이디입니다."));
        pizzaRepository.delete(target);
        return target.createPizzaDto();
    }
}
