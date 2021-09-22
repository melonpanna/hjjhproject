package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlller {

    @GetMapping("/")//로컬호스트 8080 첫번째 화면
    public String home(){
        return "index";
    }
}
