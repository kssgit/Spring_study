package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //해당 값이 널일 경우 Optional에 감싸서 반환 한다
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
