package hello.hellospring.service;

import hello.hellospring.domain.MemberForm;
import hello.hellospring.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class MemberServiceTest {

    //logger
    Logger log = (Logger) LoggerFactory.getLogger(MemberServiceTest.class);

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        //given
        MemberForm memberForm = new MemberForm("testuser",
                "ㅎㅈ", "hi2804@naver.com", LocalDate.now(), "mehhhhhh");
        memberService.join(memberForm);

//        Member member = memberService.loadUserByUsername("testuser");
//        LocalDate date = member.getBirth();

    }
}
