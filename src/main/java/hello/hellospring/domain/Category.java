package hello.hellospring.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {

    @Id
    @Column(name="id")
    private Long id;

    @Column(unique = true)
    private String userId;


    private int postCount=0; //글 수

    private int publicPostCount=0; //


//    @OneToMany(mappedBy = "category")
//    private List<Post> postList = new ArrayList<Post>();


    public Category(){}

    @Builder
    public Category(Long id, String userId) {
        this.id=id;
        this.userId = userId;
    }

}
