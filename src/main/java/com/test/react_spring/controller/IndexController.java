package com.test.react_spring.controller;


import com.test.react_spring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    private final MemberService memberService;

    public IndexController(MemberService memberService) {
        this.memberService = memberService;
    }

    //main page
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @PostMapping("/")
    public String search(LoginForm form) {

        memberService.singIn(form);
        return "";
    }

}
