package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form) {
        Article articleEntity = form.toEntity();
        return articleRepository.save(articleEntity);
    }

    // PATCH
    // 업데이트된 엔티티 응답
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
        // 1. Dto -> Entity
        Article articleEntity = form.toEntity();
        log.info("id: {}, articleEntity: {}", id, articleEntity);
        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if(target == null || id != articleEntity.getId()) {
            // 400, 잘못된 요청 응답
            log.info("id: {}, articleEntity: {}", id, articleEntity);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답
        target.patch(articleEntity); // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target); // 수정된 기존 데이터 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리
        if(target == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        // 3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
