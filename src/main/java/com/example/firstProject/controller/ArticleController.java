package com.example.firstProject.controller;

import com.example.firstProject.Dto.ArticleForm;
import com.example.firstProject.Repository.ArticleRepository;
import com.example.firstProject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링부트가 미리 생성한 객체를 가져다가 자동연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleFrom(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ //화면에서 내용 던지면 여기로 옴 ( new.mustache에서 form action+method 주소지가 여기임)
        //System.out.println(form.toString()); --> 로깅
        log.info(form.toString());

        // 1. Dto 변환 = 엔티티
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. 리포지토리에게 엔티티를 dB안에 저장하도록
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();//리다이렉트
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //PathVariable --> URL의 id를 전달
        log.info("id="+id);

        // 1. id로 데이터를 가져온다.
        //데이터를 가져오는 주쳬 : 리파지토리
        Article articleEntity = articleRepository.findById(id).orElse(null); //id값을 찾을 때, 없으면 null반환
        // Optional<Article> articleEntity = articleRepository.findById(id); // 자바8버전

        // 2. 가져온 데이터를 모델에 등록한다.
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정한다.
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        //1. 모든 Article을 가져온다
        //List<Article> articleEntityList = (List<Article>) articleRepository.findAll(); // 타입 케스팅
        //Iterable<Article> articleEntityList = articleRepository.findAll(); // findAll()의 반환값인 Iterable<T>로 선언
        List<Article> articleEntityList = articleRepository.findAll(); // findAll()을 오버라이드

        //2. 가져온 Article묶음을 뷰로 전달하기
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable  Long id, Model model){
        //수정할 데이터 가져오깅
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. dto 를 엔티티로 변환
        Article  articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 디비에 저장
        // 2-1 기존데이터 가져오깅
        //Optional<Article> target = articleRepository.findById(articleEntity.getId());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 업뎃
        if(target!=null){
            articleRepository.save(articleEntity); // 엔디티 업뎃
        }
        //3. 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElse(null);

        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","Delete Complete!");
        }

        return "redirect:/articles";
    }
}
