package com.example.firstProject.Dto;

import com.example.firstProject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment cmt) {
        return new CommentDto(
                cmt.getId(),
                cmt.getArticle().getId(),
                cmt.getNickname(),
                cmt.getBody());
    }
}
