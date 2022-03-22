package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServic®eImpl();
        //Spring container에 AppConfig 등록
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //등록된 AppConfig에서 MemberService 불러오기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//(Bean 이름, 클래스 타입)

        //가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());//가입할 때 사용한 member
        System.out.println("find Member = " + findMember.getName());// 가입 후 조회한 member

    }
}
