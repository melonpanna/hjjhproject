package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PostDao  extends JpaRepository<Post,Long> {

    public Post save(Post post);
    public Page<Post> findById(Long id, Pageable pageable);

    public Page<Post> findByName(String name, Pageable pageable);

    public Page<Post> findByRegDate(Date regDate,Pageable pageable);

//    public Page<Post> findByMonth(int month, Pageable pageable);
    public Page<Post> findAllByRegDateBetween(Date start,Date end,Pageable pageable);
}
