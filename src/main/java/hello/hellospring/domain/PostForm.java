package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PostForm {

    @NotBlank(message = "로그인 필요")
    private String userId;

    @Size(min=1,max=50)
    private String title;

    @NotBlank(message = "날짜 지정이 필요합니다.")
    private LocalDate regDate;

    @Size(min=1)
    private String contents;

    public PostForm(){}

    public PostForm(String userId, String title, LocalDate regDate, String contents) {
        this.userId = userId;
        this.title = title;
        this.regDate = regDate;
        this.contents = contents;
    }

}
