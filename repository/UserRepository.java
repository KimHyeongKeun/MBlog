package com.example.blog.repository;

//DAO
//자동으로 bean등록이 된다.
//@Reposiory가 생략가능하다.
//public interface UserRepository extends JpaRepository<User, Integer> {  //해당 JpaRepository User테이블을 관리하는 repository과 Usertable의 primary key는 integer다.
//
//    //SELECT * FROM user WHERE username =1?
//    Optional<User> findByUsername(String username);
//
//}

//이렇게만 들고 있어도 JpaRepository가 가지고 있는 함수 사용 가능하다.

//JPA Naming 쿼리
//SELECT * FROM user WHERE username= ? AND Password =?
//User findByUsernameAndPassword(String username, String password);

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Query(value = "SELECT * FROM user WHERE username= ?1 AND Password =?2", nativeQuery = true)
//User login(String username, String password);
public interface UserRepository extends JpaRepository<User, Integer> {
//  JPA Naming 쿼리
//  SELECT * FROM user WHERE username = ?1 AND password = ?2;
//  User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);

    Optional<User> findByUsername(String username);
}