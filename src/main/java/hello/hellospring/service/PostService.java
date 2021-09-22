package hello.hellospring.service;

import hello.hellospring.domain.Post;
import hello.hellospring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //저장
    @Transactional
    public Long save(Post post){
        return postRepository.writePost(post).getId();
    }

//    public Page<Post> getPostList(Long categoryId, Pageable pageable){
//        return postRepository.getPostList(categoryId,pageable);
//    }

    public Page<Post> getPostList(String name, Pageable pageable){
        return postRepository.getPostList(name,pageable);
    }

    //현재 month
    public Page<Post> getPostMonthList(String name, Pageable pageable) {
        return postRepository.getPostListByMonth(new Date(), pageable);
    }

    //지정 month
    public Page<Post> getPostMonthList(String name, Date date, Pageable pageable) {
        return postRepository.getPostListByMonth(date, pageable);
    }

    //수정
//    public Long update(Long id,){
//
//    }
    
    //삭제
//    public void delete(Long id){
//        Posts posts=postRepository;
//
//    }



}
