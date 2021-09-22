package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import hello.hellospring.domain.Category;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
//@Table(name="post")
@Table(name="bufpost")

public class Post {

    @Id
    @GeneratedValue
    private Long id;

//    @Min(value=1)
//    private Long category_id;

//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;

    @Column
    private String name;    //작성자 이름

    @Size(min=1,max=100)
    @Column(name="title", nullable = false)
    private String title;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="regDate")
    private Date regDate;

    @Column
    private String contents;

}
