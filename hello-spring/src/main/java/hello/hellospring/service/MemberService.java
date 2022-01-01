package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    //회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
//        Optional<Member> result =repository.findByName(member.getName());
//        result.ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });
        validateDuplicateMember(member); // 중복 회원 검증 ctrl + alt + M
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

//   전체회원 조회
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return repository.findById(memberId);
    }
}
