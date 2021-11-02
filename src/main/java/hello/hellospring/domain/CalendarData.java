package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CalendarData")
public class CalendarData {

    @Id
    @GeneratedValue
    @Column(name = "calendardata_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "place")
    private String place;

    @Column(name = "contents")
    private String contents;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    //==생성 메서드==//
    public static CalendarData createCalendarData(Member member, String title,
                                                  String place, String contents,
                                                  LocalDate startDate, LocalDate endDate) {
        CalendarData calendarData = new CalendarData();
        calendarData.setMember(member);
        calendarData.setTitle(title);
        calendarData.setPlace(place);
        calendarData.setContents(contents);
        calendarData.setStartDate(startDate);
        calendarData.setEndDate(endDate);

        return calendarData;
    }

    protected CalendarData(){}

}
