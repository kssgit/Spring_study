package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    //실무에서는 동시성 issue 때문에  ConcurrnetHashMap 을 사용한다
    //자세한 사항은 https://devlog-wjdrbs96.tistory.com/269 참조

    @Override
    public void save(Member member) {
        store.put(member.getId(),  member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }


}
