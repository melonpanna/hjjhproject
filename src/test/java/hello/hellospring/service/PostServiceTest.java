package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.PostForm;
import hello.hellospring.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    MemberService memberService;

    @Autowired
    PostRepository postRepository;


    @Test
    public void 글작성() throws Exception {
//        PostForm postForm = new PostForm("testuser", "제목!!22", LocalDate.now(), "내용입니다..");
        PostForm postForm = new PostForm("testuser", "start", LocalDate.of(2021,9,1), "시작일");
        postService.save(postForm);

    }

    @Test
    public void 리스트검색() throws Exception {
        Member member = memberService.loadUserByUsername("testuser");
        postService.getPostMonthList(member);
    }

}
