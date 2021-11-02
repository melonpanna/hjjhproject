package hello.hellospring.service;

import hello.hellospring.domain.CalendarForm;
import hello.hellospring.repository.CalendarDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarServiceTest {

    @Autowired
    CalendarDataService calendarDataService;
    @Autowired
    CalendarDataRepository calendarDataRepository;

    @Test
    public void 데이터등록() {

        CalendarForm calendarForm = new CalendarForm("testuser", "제목!!", "Home", "내용.. ", LocalDate.now(), LocalDate.now());
        calendarDataService.save(calendarForm);

    }


}
