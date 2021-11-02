package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Post;
import hello.hellospring.domain.PostForm;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    //repository
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
//    private final CalendarRepository calendarRepository;

//    @Autowired
//    public PostService(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }

    //저장
//    @Transactional
    public Long save(PostForm postForm)
    {
        //userid를 통하여 entity 검색
        Member member = memberRepository.findByUserId(postForm.getUserId());

        Post post = Post.createPost(member,postForm.getTitle(),postForm.getContents(),postForm.getRegDate());
        return postRepository.save(post);
    }

    public Post findOne(Long id) {
        return postRepository.findOne(id);
    }

    public List<Post> getPostList(Member member){
        return postRepository.findAllByMember(member);
    }

    //현재 month
    public List<Post> getPostMonthList(Member member) {
        return postRepository.findAllByMemberAndMonth(member, LocalDate.now(ZoneId.of("Asia/Seoul")));
    }

    //지정 month
    public List<Post> getPostMonthList(Member member, LocalDate date) {
        return postRepository.findAllByMemberAndMonth(member,date);
    }

//    수정
//    public Long update(Long id,){
//
//    }
    
    //삭제
//    public void delete(Long id){
//        Posts posts=postRepository;
//
//    }



}
