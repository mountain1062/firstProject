package com.example.firstProject.API;

import com.example.firstProject.Dto.CommentDto;
import com.example.firstProject.Repository.CommentRepository;
import com.example.firstProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //댓글 목록조회
    @GetMapping("/api/articles/{articleid}/comment")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleid){

        List<CommentDto> dtos = commentService.comments(articleid);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성
    @PostMapping("/api/articles/{articleid}/comment")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleid, @RequestBody CommentDto dto){
        CommentDto createDto =commentService.create(articleid,dto);

        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    //댓글 수정
    @PatchMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){

        CommentDto updateDto =commentService.update(id,dto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){

        CommentDto updateDto =commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
}
