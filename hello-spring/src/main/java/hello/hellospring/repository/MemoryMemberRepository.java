package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*Spring 컨테이너에서 관리하도록 등록*/
//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long , Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
               .filter(member -> member.getName().equals(name))
               .findAny(); // 하나라도 찾으면 해당 값 반환
        //없으면 알아서 null값을을optional로 감싸서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 보통은 arraylist로 반환한다
    }

    //store에 저장된 모든 객체 삭제
    public void clearStore(){
        store.clear();
    }

}
