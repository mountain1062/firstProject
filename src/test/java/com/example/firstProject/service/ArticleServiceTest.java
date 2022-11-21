package com.example.firstProject.service;

import com.example.firstProject.Dto.ArticleForm;
import com.example.firstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        //예상
        Article a = new Article(1L,"가가가","으어어");
        Article b = new Article(2L,"나나냐","으어어");
        Article c =  new Article(3L,"다다다","으어어");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공() {
        //예상
        Long id = 1L;
        Article expected = new Article(id,"가가가","으어어");

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected.toString(),article.toString());
    }



    @Test
    @Transactional//테스트에도 롤백 필요
    void create_성공_타이틀_content_만() {
        //예상
        String title = "라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional//테스트에도 롤백 필요
    void create_실패() { // id포함된 dto
        //예상

        String title = "라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected = null;

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected,article);
    }


}