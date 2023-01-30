package com.example.blog.controller.api;


//
//@RestController
//public class UserApiController {
//
//    @Autowired
//    private  UserService userService;
//
//
//
//    @PostMapping("/auth/joinProc")
//    public ResponseDto<Integer> save(@RequestBody User user){   //username, password, email
//        System.out.println("UserApiController: save호출됨");
//        //실제 DB에 insert를 하고 아래에서 return이 되면된다.
//        user.setRole(RoleType.USER);
//         userService.회원가입(user);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //
//    }
//
//    //전통적인 방식의 로그인 방식
////    @PostMapping("/api/user/login")
////    public ResponseDto<Integer> login(@RequestBody User user){
////        System.out.println("UserApiContoller: login호출됨");
////        User principal = userService.로그인(user);     //principal(접근주체)
////
////        if(principal!= null){
////            session.setAttribute("principal",principal);
////        }
////
////        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
////    }
//
//
//}

import com.example.blog.dto.ResponseDto;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }

}
