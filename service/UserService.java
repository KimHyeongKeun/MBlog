package com.example.blog.service;


import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다.IOC를 해준다. 메모리에 대신 띄워준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();    //1234원문
        String encPassword = encoder.encode(rawPassword);   //해시
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    //사이트 안에서의 로그인 -> security를 사용하지 않은 방식의 로그인
//    @Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜젝션 종료(정합성)
//    public User 로그인(User user){
//        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//
//    }
}
