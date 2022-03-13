package hello.core.member;

public interface MemberRepository {

    void  save(Member member); // 사용자 저장

    Member findById(Long memberId); // 사용자 ID 찾기
}
