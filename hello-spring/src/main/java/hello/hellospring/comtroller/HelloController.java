package hello.hellospring.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        //model은 view 에서 랜더링 할 때 쓰이는 객체
        model.addAttribute("data","hello!!");
        return "hello"; // resources/templates/hello.html 실행 시켜라
        // return 은 기본경로로 resources/templates/ 아래에서 찾는다
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name , Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http 바디에 데이터 삽입
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

//    Json 방식
//    객체를 넘긴다
//    객체를 반환할 경우 JsonConverter가 동착해 Json으로 변환 후 넘긴다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello(); // 자동 완성 Ctrl + Shift + Enter
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
//       Alt + insert 하면 자동으로 getter setter 생성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
