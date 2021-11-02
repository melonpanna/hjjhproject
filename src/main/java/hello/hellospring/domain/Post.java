package hello.hellospring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "member_id") //join되는 column 이름
    private Member member;

    @Column(name = "reg_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate regDate;

//    @Column
//    private String name;    //작성자 이름

    @Size(min=1,max=100)
    @Column(name="title", nullable = false)
    private String title;

    @Column
    private String contents;

    //==생성 메서드==//
    public static Post createPost(Member member, String title, String contents, LocalDate localDate) {
        Post post = new Post();
        post.setMember(member);
        post.setTitle(title);
        post.setContents(contents);
        post.setRegDate(localDate);

        return post;
    }

    protected Post() {}
}
