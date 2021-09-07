package hello.hellospring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userId;

//    private Date regDate;

    private int postCount; //글 수

    private int publicPostCount; //

}
