package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Repository
public class PostRepository {

    @Autowired
    private PostDao postDao;

    //logger
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);


//    public Page<Post> getPostList(Long categoryId,Pageable pageable) {
//        Page<Post> postPage = postDao.findById(categoryId,pageable);
//
//        return postPage;
//    }

    //모든 post 출력
    public Page<Post> getPostList(String name,Pageable pageable) {
        Page<Post> postPage = postDao.findAll(pageable);
        return postPage;
    }

    public Page<Post> getPostListByMonth(Date date, Pageable pageable){
        int year = date.getYear();
        int month=date.getMonth();

        Page<Post>postPage=findByMonth(year,month,pageable);

        return postPage;
    }

    private Page<Post> findByMonth(int year,int month, Pageable pageable) {

        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,1);

        Date start=new Date(year,month,1);
        Date end=new Date(year,month, cal.getActualMaximum(Calendar.DAY_OF_MONTH)-1);       //왜인지는모르겟지만.

        return postDao.findAllByRegDateBetween(start,end,pageable);
    }

    public Post getPostById(Long id) throws IllegalArgumentException{
        Post post = postDao.getOne(id);

        if(post==null){
            throw new IllegalArgumentException("Post Not Found");
        }
        return post;
    }

    public Post writePost(Post post){
        return postDao.save(post);
    }

    //작성자 확인
    public boolean isThisUserPostWriter(Long id) throws IllegalArgumentException{
        Post post=getPostById(id);

        return post.getId().equals(""/*userId*/);
    }

}
