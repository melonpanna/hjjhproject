package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.PostForm;
import hello.hellospring.domain.Member;
import hello.hellospring.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@RequiredArgsConstructor
//@EnableWebSecurity
@Controller
public class PostController {

    //logger
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    /**
     * 목록 출력
     *
     * @return
     */
    @GetMapping(value = {"/list", "/list/{year}/{month}"})
    public String list(@PathVariable(required = false) Integer year, @PathVariable(required = false) Integer month,
                       Model model, Authentication authentication) throws Exception {


        //현재 user
        Member member = (Member) authentication.getPrincipal();
        String username = member.getUsername();

        logger.info("username : " + username);

        List<Post> list;
        if (year == null || month == null) { //현재 month
            list = postService.getPostMonthList(member);
        } else {
            LocalDate date = LocalDate.of(year, month, 1);
            list = postService.getPostMonthList(member, date);
            model.addAttribute("year", year);
            model.addAttribute("month", month);

            logger.info("mode attribute : " + year + month);
        }

        model.addAttribute("postList", list);
        return "list";
    }


    //==글 작성==//
    @GetMapping("posts/write")
    public String writeForm(PostForm postForm, Model model) {
        return "posts/write";
    }

    @PostMapping("/posts/write")
    public String write(@Valid PostForm postForm, Errors errors, Model model) {


        if (errors.hasErrors())
            return "posts/write";

        return "posts/" + postService.save(postForm);
    }

    //==글 수정==//


    //==글 조회==//
//    @GetMapping("posts/{id}")
//    public String getPost(@PathVariable(required = true) Long id) {
////        postService.findOne(id);
//    }
}