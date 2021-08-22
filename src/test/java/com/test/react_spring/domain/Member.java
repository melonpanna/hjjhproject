package hello.hellospring.domain;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity //jpa가 관리하는 엔티티
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;        //

    @Column(name = "name")
    private String name;

    @Column(name = "userid", unique = true)
//    private String user;
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name="birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime birth;

    @Column(name = "auth")
    private String auth;

    @Builder
    public Member(String name, String userId, String email, LocalDateTime birth, String auth) {
        this.name=name;
        this.userId = userId;
        this.email=email;
        this.birth = birth;
        this.auth=auth;
    }

    public Member(){}

    //사용자의 권한(auth)를 컬렉션 형태로 변환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role:auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //number
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //userId 반환
    //사용자의 unique한 값을 반환
    @Override
    public String getUsername() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //pw
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 판단
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    //계정 잠금 여부 판단
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    //패스워드 만료 여부 판단
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //계정 사용 가능 여부 판단
    @Override
    public boolean isEnabled(){
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth(LocalDateTime date){
        this.birth =date;
    }

    public String getDate(String date){
        return date;
    }

}
