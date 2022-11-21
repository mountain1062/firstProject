package com.example.firstProject.service;

import com.example.firstProject.Dto.ArticleForm;
import com.example.firstProject.Repository.ArticleRepository;
import com.example.firstProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언 ( 서비스 객체를 스프링부트에 생성하는 어노테이션)
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
       return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        return (article.getId() != null) ? null : articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        log.info("in service get dto : {}",dto.toString());
        Article article = dto.toEntity();
        log.info("in service article id : {}, dto : {}",article.getId(),article.toString());
        Article target = articleRepository.findById(id).orElse(null);
        log.info("in service find target id : {}, dto : {}",target.getId(),target.toString());

        if (target == null || id != article.getId()) {
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;


    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 정상종료 되지않으면 롤백
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 엔티티 묶음으로 전환
        List<Article> articlesList = dtos.stream()
                .map(dto->dto.toEntity())
                .collect(Collectors.toList());

        /*위 stream문을 for문으로 작성해보면
            List<Article> articlesList = new ArrayList<>();
            for(int i=0;i<dto.size();i++){
                ArticleForm dto = dtos.get(i);
                Article entity = dto.toEntity();
                articleList.add(entity);
            }
        * */

        // 엔티티 묶음을 DB에 저장
        articlesList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제예외
        articleRepository.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("실패!")
        ); // 람다식?

        //결과값 반환
        return articlesList;
    }
}
