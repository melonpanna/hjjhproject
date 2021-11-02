package hello.hellospring.service;

import hello.hellospring.domain.CalendarData;
import hello.hellospring.domain.CalendarForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.CalendarDataRepository;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarDataService {

    private final CalendarDataRepository calendarDataRepository;
    private final MemberRepository memberRepository;

    public Long save(CalendarForm calendarForm) {
        LocalDate date = calendarForm.getEndDate();
        if (date == null) {
            date = calendarForm.getStartDate();
        }

        Member member = memberRepository.findByUserId(calendarForm.getUserId());
        CalendarData calendarData = CalendarData.createCalendarData(member, calendarForm.getTitle(), calendarForm.getPlace(), calendarForm.getContents(),
                calendarForm.getStartDate(), date);

        return calendarDataRepository.save(calendarData);
    }

    public List<CalendarData> getCalendarDataMonthList(Member member) {
        return calendarDataRepository.findAllByMemberAndMonth(member, LocalDate.now(ZoneId.of("Asia/Seoul")));
    }

    public List<CalendarData> getCalendarDataMonthList(Member member, LocalDate date) {
        return calendarDataRepository.findAllByMemberAndMonth(member, date);
    }

    //수정
//    public Long update(Long id,) {
//
//    }

    //삭제
}
