package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class MemberForm { //회원가입 화면에서 데이터를 전달 받을 폼 객체

//    private Long id; 왜잇지
    @NotBlank(message = "아이디를 비워둘 수 없습니다.")
    private String userId;

    @NotBlank(message = "비밀번호를 비워둘 수 없습니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\W)(?=\\\\S+$).{8,20}",
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@$%^*]).{8,20}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "이름을 비워둘 수 없습니다.")
    private String name;

    //시발 ..
    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @NotBlank(message="생일을 입력해 주세요.")
    private LocalDate birth;

    @NotBlank(message = "이메일을 비워둘 수 없습니다.")
    @Email(message = "email 형식 불일치")
    private String email;

    public MemberForm(String userId, String name, String email, LocalDate birth, String password) {

//        this.id = id;
        this.userId=userId;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.password = password;
    }

    public MemberForm(){}

}
