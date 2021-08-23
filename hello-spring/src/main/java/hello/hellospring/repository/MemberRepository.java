package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //인터페이스는 접근 제한자가 없어도 기본으로 public static final이 적용 된다
    Member save(Member member);
    Optional<Member> findById(Long id);
    //해당 값이 널일 경우 Optional에 감싸서 반환 한다
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
