package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    //대용량 데이터를 사용할 때
    private String content; //섬머노트 라이브러리<html>태그가 섞여서 디자인이 된다.

    private int count;//조회수

//    @ManyToOne(fetch = FetchType.EAGER) //Board가 many고 One이 유저가 된다. -> 여러 개의 게시물은 한 명의 유저에 의해서 쓰여질 수 있다.
    @ManyToOne
    @JoinColumn(name = "userId")    //만들때 userId로 데이터베이스로 들어간다.
    private User user;  //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있기에 FK를 사용하지 않아도 된다.

//    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //하나의 게시물은 여러 개의 답변을 가질 수 있다.  //mappedBy는 연관관계의 주인이 아니다 (난 FK를 만들지 마라)
    @OneToMany(mappedBy = "board")
    private List<Reply> reply;  //joincolumn이 필요가 없다. rely가 늘어날지 모르는데 replyid를 지정해두면 추가될 때 마다 1정규화를 위배하기 때

    @CreationTimestamp
    private Timestamp createDate;


}
