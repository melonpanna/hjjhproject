//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//                        //jpa리포지토리는 JpaRepository를 상속받아야 한다.<T,ID:식별자PK>    다중상속
//public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
//
//    Optional<Member> findByUser(String user);
//    Optional<Member> findByName(String name);
//    Optional<Member> findByUserId(String userId);
//    Optional<Member> findByEmail(String email);
//
//    Optional<Member> findByPw(String pw);
//
//
//}
