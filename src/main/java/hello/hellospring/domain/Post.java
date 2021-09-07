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
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Min(value=1)
    private Long categoryId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="categoryId",insertable = false,updatable = false)
    private Category category;

    @Size(min=1,max=100)
    @Column(nullable = false)
    private String title;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regDate;

    private String contents;

}
