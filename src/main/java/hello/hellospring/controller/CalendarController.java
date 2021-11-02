package hello.hellospring.controller;

import hello.hellospring.domain.CalendarForm;
import hello.hellospring.service.CalendarDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    //logger
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final CalendarDataService calendarDataService;

    @GetMapping("/calender")
    public String showCalender() {

        return "calendar";
    }

    @PostMapping("/calender/save")
    public String saveCalender(@Valid CalendarForm calendarForm){
        calendarDataService.save(calendarForm);
        return "redirect:/calender";
    }


}

