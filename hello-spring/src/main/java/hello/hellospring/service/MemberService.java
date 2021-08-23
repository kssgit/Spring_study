package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//서비스 로직은 매서드 명이 비즈니스 적이여야 한다
public class MemberService {
    // Test 케이스 쉽게 만드는 법
    //Ctrl + Shift + T 를 이용하여 test케이스를 바로 만들 수 있다
    private final MemberRepository memberRepository ;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
        /*
        Optional<Member> result = memberRepository.findByName(member.getName()); //Ctrl + Alt + V 하면 알아서 변수명 생성 해준다

        //result.orElseGet() 도 쓸 수 있다
        //result 의 값이 있으면 아래 람다식 동작 (Optional이기 때문에 가능)
        result.ifPresent(m -> {
            throw new IllegalStateException("이미존재하는 아이디 입니다");
        });
        */

        //위의 코드 간략화
        validateDuplicateMember(member); //매서드 추출 하기 Ctrl + Atl + M

        memberRepository.save(member);
        return member.getId();
    }
    //전체 회원 조회
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    //중복회원 조회
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 아이디 입니다");
                        });
    }

}
