package com.example.firstProject.service;

import com.example.firstProject.Dto.CommentDto;
import com.example.firstProject.Repository.ArticleRepository;
import com.example.firstProject.Repository.CommentRepository;
import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleid) {
        /*List<Comment> comments = commentRepository.findByArticleId(articleid);

        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0;i<comments.size();i++){
            Comment cmt = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(cmt);
            dtos.add(dto);
        }
        return dtos;*/

        //위 코드를 스트림 문법으로
        return commentRepository.findByArticleId(articleid)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleid, CommentDto dto) {
        // 예외발생
        Article article = articleRepository.findById(articleid).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음") );

        // 엔티티생성
       Comment comment = Comment.createCommnet(dto, article);

        // 저장
        Comment created = commentRepository.save(comment);

        //DTO로 변환하여 반환
        return CommentDto.createCommentDto(comment);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없음") );

        comment.patch(dto);

        Comment updated = commentRepository.save(comment);

        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment tar = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없음") );

        commentRepository.delete(tar);

        return CommentDto.createCommentDto(tar);//삭제된 데이터 내용 던지기
    }
}
