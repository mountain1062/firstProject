package com.example.firstProject.entity;

import com.example.firstProject.Dto.CommentDto;
import com.example.firstProject.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 여려 댓글(Many)이 (To) 하나의 게시글(One)에 연결됨
    @JoinColumn(name = "article_id")//DB에 article_id로 컬럼이 추가 생성됨.
    private Article article;

    @Column
    private String nickname;

    @Column
    private  String body;

    public static Comment createCommnet(CommentDto dto, Article article) {
        // 에외처리
        if(dto.getId() != null)
            throw new IllegalArgumentException("Failed! don't insert -->[id]<-- ");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("Failed! Wrong -->[articleID]<--");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );

    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId())
            throw new IllegalArgumentException("id입력오류");

        if(dto.getNickname() !=null)
            this.nickname  = dto.getNickname();

        if(dto.getBody() !=null)
            this.body = dto.getBody();
    }
}
