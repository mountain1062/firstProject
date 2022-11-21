package com.example.firstProject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity// 해당 클래스로 테이블을 만드는 @(어노테이션)
@AllArgsConstructor // 생성자 자동완성
@NoArgsConstructor //디폴트 생성자
@ToString
@Getter//
public class Article {

    @Id // 대표값 지정(고유번호 같은거)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id 값을 자동으로 생성 1,2,3, ...를 DB가 알아서 [@생성된값(전략 = DB가 알아서~)]
    private Long id; // 대표값

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }

}
