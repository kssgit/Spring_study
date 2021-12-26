package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","Hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name") String name , Model model){
        //@RequestParam(value = "파라미터 이름" , required = true)
        // required 디폴트 값은 true 이다 - 파라미터를 꼭 넘겨야 하면 true, 꼭넘기지 않아도 될 경우 false
        model.addAttribute("name", name);// 파라미터를 모델에 담아서 template에 넘긴다
        return "hello-template";
    }

    // http 응답의 바디에 return 값을 넘겨 주겠다
    // ViewResolver에게 넘기지 않고 Http응답에 바로 넘긴다
    // 사용되는 라이브러리 StringHttpMessageConverter
    @GetMapping("hello-string") // 반환 값 : 기본 문자
    @ResponseBody
    public String helloString(@RequestParam("name") String name){

        return "hello " + name;
    }

    //객체가 넘어갈 경우 디폴트로 JSON방식으로 데이터를 만들어서 Http응답에 반환
    //사용되는 라이브러리 MappingJackson2HttpMessageConverter
    @GetMapping("hello-api") // 반환 값 : 객체
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        // return 값을 객체로 넘기게 될경우 JSON값으로 변환해서 넘어간다
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello { // java bean에서 값을 꺼낼 때 쓰는 방식
        private String name ;

        //alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
