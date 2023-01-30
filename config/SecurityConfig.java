package com.example.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.blog.config.auth.PrincipalDetailService;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration  //빈등록(IOC관리)
@EnableWebSecurity  //시큐리티 필터를 추가 등록된다. = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 한다.
@EnableGlobalMethodSecurity(prePostEnabled = true)  //특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean   //IOC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인해주는데 password를 가로채는데
    //해당 password가 뭘로 해시가 되어 회원가입이 되었는지 알아야
    //같은 해시로 암호화해서 DB에 있는 해시랑 비교.

    //2.시큐리티가 로그인할 때 어떤 암호화로 인코딩해서 비번을 비교할지 알려줘야한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    //3. 필터링
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()   //crsf 토큰 비활성화(테스트시 걸어두는게 좋다.)
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/");//스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인해준다.



    }

}
