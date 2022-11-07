package com.example.firstProject.Repository;

import com.example.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> { // Crud리포지토리 = 기본제공 기능 상속 <관리대상, 대표값 타입>

}
