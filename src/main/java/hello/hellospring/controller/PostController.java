package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.PostForm;
import hello.hellospring.repository.CategoryRepository;
import hello.hellospring.service.PostService;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@EnableWebSecurity
@Controller
public class PostController {

    private final PostService postService;

    private CategoryRepository categoryRepository;
    /**
     *  list
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @GetMapping("posts/write")
    public String writeForm(PostForm postForm, Model model){
        model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

        return "posts/writeForm";
    }

    @PostMapping("/posts/write")
    public String write(@Valid Post post, Errors errors,Model model){
        model.addAttribute("categoryMap", categoryRepository.getCategoryMap());

        if(errors.hasErrors())
            return "posts/writeForm";

        return "posts/"+postService.save(post);
    }



}