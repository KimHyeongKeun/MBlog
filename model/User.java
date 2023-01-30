package com.example.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> 자바(다른 언어) Object -> 테이블로 매핑해주는 기술
 // User 클래스가 MySQL에 테이블이 생성된다.

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@DynamicInsert    //insert시 null인 필드를 제외시켜준다.
@Entity
public class User {

    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;          //sequence(Oracle), auto_increment(MySQL)

    @Column(nullable = false, length = 30, unique = true) //colunm이 null이 될 수가 없다.
    private String username; //아이디

    @Column(nullable = false, length = 100) //암호를 길게 설정하는 이유는 12345일 경우 이것을 해쉬로 비밀번호를 암호화하기 위함이다.
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")    //따옴표를 붙여서 문자라는 것을 알린다.

    @Enumerated(EnumType.STRING)    //DB는 RoleType이라는게 없다. 따라서 여기서 추가
    private RoleType role;  //Enum을 쓰는게 좋다.   //ADMIN,USER

    @CreationTimestamp  //시간이 자동으로 입력이 된다.
    private Timestamp createDate;

}
