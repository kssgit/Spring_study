package hello.core.member;

public interface MemberRepository {

    //저장
    void save(Member member);


    Member findById(Long memberId);

}
