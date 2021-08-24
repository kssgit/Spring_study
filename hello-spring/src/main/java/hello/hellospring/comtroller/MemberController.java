package hello.hellospring.comtroller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*Controller Service Autowired 는 모두
* Component 스캔과 자동 의존관계설정 방식 입니다. (싱글톤으로 등록 한다)
* 해당 Component들은 해당 스프링 부트의 메인 메서드가 있는
* HelloSpringApplication의 하위 패키지에서만 동작 됩니다.*/


@Controller
public class MemberController {
    /*MemberService를 사용할 때 New를 이용해 새로운 객체를 만들 경우
    * 다른 Controller에서도 MemberService를 사용할 수 있기 때문에 권장하지 않는다
    * 따라서 Spring 컨테이너에 등록하고 받아다 쓰면 된다.
    * 이럴 때 쓰는것이 @Autowired (스프링 빈으로 등록된 것과 연결해주는 작업을 해준다)
    * 해당 어노테이션은 스프링 빈으로 등록된 클래스에서만 쓸 수 있다*/
    public final MemberService memberService;

    //@Autowired private MemberService memberService;  -> DI 필드 주입( 별로 좋진 않다)

    /*@Autowired //DI Setter 방법 (실행중에 동적으로 변환 될 가능성이 있다)
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }*/

    @Autowired //DI 생성자 주입 ( 빈에 올라가는 시점에 생성되기 때문에 안전하다)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
