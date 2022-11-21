package com.example.firstProject.service.Repository;

import com.example.firstProject.Repository.CommentRepository;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // case1 : 4번 게시글의 모든 댓글 조회
        {
            //입력데이터 준비
            Long articleId = 4L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상
            Article article = new Article(4L,"당신의 인생영화?","댓ㄱ" );
            Comment a = new Comment(1L,article,"마운틴백","레옹");
            Comment b = new Comment(2L,article,"김정은","태극기휘날리며");
            Comment c = new Comment(3L,article,"봉준호","주유소습격사건");
            List<Comment> expected = Arrays.asList(a,b,c);

            //검증
            assertEquals(expected.toString(),comments.toString(), "4번글의 모든 댓글 출력");
        }

        // case2 : 1번 게시글의 모든 댓글 조회
        {
            //입력데이터 준비
            Long articleId = 1L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상
            Article article = new Article(1L,"가가가","으어어" );

            List<Comment> expected = Arrays.asList();

            //검증
            assertEquals(expected,comments, "1번글은 댓글 없음");
        }

    }

    @Test
    void findByNickname() {
    }
}