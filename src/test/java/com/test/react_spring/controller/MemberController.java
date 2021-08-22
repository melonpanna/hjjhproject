package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    private final MemberService memberService;

    //logger
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @GetMapping("/signUp")
    public String createForm(MemberForm memberForm){
        return "/signUp";
    }
    /**
     *회원가입
     */
    @PostMapping("/signUp")
    public String create(@Valid MemberForm memberForm, Errors errors, Model model){
        //패턴체크
//    public String create(MemberForm form) {

//        logger.info("create시도");
        if (errors.hasErrors()) {   //create 실패

            logger.info("create실패");            //
            //입력 데이터 유지
            model.addAttribute("memberForm", memberForm);

            Map<String, String> validatorResult
                    = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/signUp";
        }

        //memberService클래스의 join메서드를 실행한다.(중복검사+정보저장)
        memberService.join(memberForm);         //memberService클래스의 join메서드를 실행한다.(중복검사+정보저장)
        return "redirect:/";
        //회원가입이 끝나고 리다이렉트로 홈화면으로 되돌아간다.
    }
    /**
     *회원조회
     */
    @GetMapping("/members")
    public String list(Model model){ //모델은 HashMap 형태를 갖고 있으므로 key값과 value값처럼 사용할 수 있다
        List<Member> members = memberService.findMembers();//리스트로 멤버를 담아둠
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
