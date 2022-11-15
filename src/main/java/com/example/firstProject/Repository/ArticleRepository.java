package com.example.firstProject.Repository;

import com.example.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // Crud리포지토리 = 기본제공 기능 상속 <관리대상, 대표값 타입>

    @Override // 오버라이드
    ArrayList<Article> findAll();
}
