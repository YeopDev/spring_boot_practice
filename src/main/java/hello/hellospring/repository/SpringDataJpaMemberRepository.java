package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    //JPQL select m Member m where m.name = ? 이런식으로 자동으로 쿼리를 짜줌. name 뒤에 ANDID 이런식으로 해도 됨.
    @Override
    Optional<Member> findByName(String name);
    //findByNameANDID(String name,Long id);
}
