package com.example.firstProject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor // 생성자 자동완성
@NoArgsConstructor //디폴트 생성자
@ToString
public class Article {

    @Id // 대표값 지정(고유번호 같은거)
    @GeneratedValue // Id 값을 자동으로 생성 1,2,3, ...
    private Long id; // 대표값

    @Column
    private String title;

    @Column
    private String content;


}
