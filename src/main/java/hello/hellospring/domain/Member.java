package hello.hellospring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.Set;

@Getter @Setter
@DynamicInsert  //null field 제외
@Entity //jpa가 관리하는 엔티티
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id",insertable = false, nullable = false)
    private Long id;        //

    @Column(name = "name")
    private String name;


    @Column(name = "userid")
//    @Column(name = "userid", unique = true)
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birth;

    @Column(name = "auth")
    private String auth;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String userid, String email, LocalDate birth, String auth) {
        this.name = name;
        this.userid = userid;
        this.email = email;
        this.birth = birth;
        this.auth = auth;
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

    //userId 반환
    //사용자의 unique한 값을 반환
    @Override
    public String getUsername() {
        return userid;
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

//    public void setPassword(String password) {
//        this.password = password;
//    }

}
