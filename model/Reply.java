package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 200)    //대용량 데이터를 사용할 때
    private String content; //섬머노트 라이브러리<html>태그가 섞여서 디자인이 된다.

    @ManyToOne  //여러 게시물은 하나의 게시물에 존재할 수 있다.
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne  //여러 개의 답변은 하나의 유저가 쓸 수 있다.
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
