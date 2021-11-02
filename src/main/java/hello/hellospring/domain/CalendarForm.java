package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CalendarForm {

    private String userId;

    @Size(min = 1, max = 10)
    private String title;

    @Size(min = 1, max = 10)
    private String place;

    @Size(min = 1,max = 100)
    private String contents;

    @NotBlank(message = "날짜 지정이 필요합니다.")
    private LocalDate startDate;

    private LocalDate endDate=null;

    public CalendarForm(){}

    public CalendarForm(String userId, String title, String place, String contents, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.title = title;
        this.place = place;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
