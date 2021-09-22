package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.PostForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.CategoryRepository;
import hello.hellospring.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

//@RequiredArgsConstructor
//@EnableWebSecurity
@Controller
public class PostController {

    //logger
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    @Autowired
    private PostService postService;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     *  list
     * @return
     */
    @GetMapping(value={"/list","/list/{year}/{month}"})
    public String list(@PathVariable(required = false) Integer year,@PathVariable(required = false) Integer month,
                       Model model, Authentication authentication,
                       @PageableDefault(sort={"id"},direction=Direction.DESC,size=5) Pageable pageable) throws Exception{



        Member member=(Member)authentication.getPrincipal();
        Long categoryId=member.getId(); //로그인 계정의 id
        String username = member.getUsername();

        //category entity 생성//
        logger.info("id(=categoryId) : " + categoryId);
        logger.info("username : " + username);

        Page<Post> list;
        if (year == null || month == null) {
            list = postService.getPostMonthList(username, pageable);
        }else{
            Date d = new Date(year-1900, month - 1, 1);
            list = postService.getPostMonthList(username, d, pageable);
        }

//        Page<Post> list=postService.getPostList(categoryId,pageable);
        list.stream().forEach(e->e.getContents());
        model.addAttribute("postList",list);

        return "list";
    }


    @GetMapping("posts/write")
    public String writeForm(PostForm postForm, Model model){
        model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

        return "posts/write";
    }

    @PostMapping("/posts/write")
    public String write(@Valid Post post, Errors errors,Model model){
        model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

        if(errors.hasErrors())
            return "posts/write";

        return "posts/"+postService.save(post);
    }



}