package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너는 HelloSpringApplication 클레스의 하위 디렉토리에 있는 것만 빈드로 등록 가능하다
public class MemberController {


    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }


}
