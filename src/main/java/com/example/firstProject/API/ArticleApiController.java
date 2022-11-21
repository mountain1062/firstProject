package com.example.firstProject.API;

import com.example.firstProject.Dto.ArticleForm;
import com.example.firstProject.Repository.ArticleRepository;
import com.example.firstProject.entity.Article;
import com.example.firstProject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired // 디펜던시 인젝션, 여기서 autowired는 ArticleService에서 어노테이션으로 생성한 서비스 객체를 가져와서 연결하는 역할
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {//@요청의 몸통에서 데이터(dto)를 받아와라
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id) {
        log.info("id : {}, dto : {}",id,dto.toString());
        Article updated = articleService.update(id,dto);


        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    //트랜젝션 :: 필수요청 실패? --> 롤백

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createList = articleService.createArticles(dtos);
        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
/*
    //get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articlerepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articlerepository.findById(id).orElse(null);
    }


    //post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){//@요청의 몸통에서 데이터(dto)를 받아와라
        Article article = dto.toEntity();
       return articlerepository.save(article);
    }

    //patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id){
        Article res = null;
        Article article = dto.toEntity();
        log.info("id:{}, article:{}",id,article.toString());

        Article target = articlerepository.findById(id).orElse(null); // db에서 가져온값
        log.info("id:{}, article:{}",id,target.toString());

        if(target == null || id!= article.getId()){ //수정 요청을할 id 엔티티가 DB에 없을떄 || 요청한 경로상 id와 JSON body내부 id가 상이할 경우
            //잘못된 응답요청
            log.info("잘못된요청 ! id:{},article:{}",id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        else {
            target.patch(article);// null값이 아닌 요소만 바꿈.
            res = articlerepository.save(target);

            return ResponseEntity.status(HttpStatus.OK).body(res); // JSON
        }
    }


    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target = articlerepository.findById(id).orElse(null);
        if(target == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articlerepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
*/
}
