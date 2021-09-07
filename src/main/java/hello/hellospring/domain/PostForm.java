package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class PostForm {

    private Long id;

    @NotBlank(message = "로그인 필요")
    private String userId;

    @NotBlank(message = "이름을 비워둘 수 없습니다.")
    private String name;

    @Size(min=1,max=100)
    private String title;

    @NotBlank(message = "날짜 지정이 필요합니다.")
    private Date regDate;

    @Size(min=1)
    private String contents;

    private Long categoryId;

}
