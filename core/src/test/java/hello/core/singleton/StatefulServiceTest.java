package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

//Singleton의 잘못된 설계
/*
싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든,
객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은
여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에
싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
무상태(Stateful) 설계를 해야한다
특정 클라이언트에 의존적인 필드가 있으면 안된다.
특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
가급적 읽기만 가능해야 한다.
필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
*/
class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService service1 = ac.getBean("staticefulService",StatefulService.class);
        StatefulService service2 = ac.getBean("staticefulService",StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        service1.order("userA", 10000);
        //ThreadB : B사용자가 20000원 주문
        service2.order("userB", 20000);

        //ThreadA : A사용자가 주문 금액 조회
        int price = service1.getPrice();
        //원했던 10000원이 나오지 않는다
        System.out.println("A사용자 주문 금액  = " + price);

        Assertions.assertThat(service1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService staticefulService(){
            return new StatefulService();
        }
    }
}