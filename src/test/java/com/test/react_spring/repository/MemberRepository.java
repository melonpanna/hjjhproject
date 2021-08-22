package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member save(Member member); //저장소에 멤버저장
    Optional<Member> findById(Long id); //저장소에서 id에 해당하는 멤버 찾아옴
    Optional<Member> findByUserId(String userId);

    Optional<Member> findByName(String name);
    List<Member> findAll();
}
