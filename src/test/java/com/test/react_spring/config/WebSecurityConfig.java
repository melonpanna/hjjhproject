package hello.hellospring.config;

import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {      //페이지 권한 설정

        http
                .authorizeRequests()
                .antMatchers( "/","/signUp").permitAll() //permit ALL
                .antMatchers("/members","myPage").hasRole("USER")
                .anyRequest().authenticated()   //나머지 요청에 대해선 권한이 있어야 접근 가능(권한 종류에 관계없이)
        .and()
                .formLogin()
                 .loginPage("/")
                 .defaultSuccessUrl("/myPage")    //로그인 성공 후 redirect
        .and()
                .logout()
                .logoutSuccessUrl("/")      //로그아웃 성공 후 redirect
                .invalidateHttpSession(true)    //세션 날리기
        ;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(new BCryptPasswordEncoder());      //사용할 passwordencoder
    }
}
