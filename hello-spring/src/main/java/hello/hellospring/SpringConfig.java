package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


/*코드로서 Bean등록 하는 방법
* 기존에 등록한 @Service @Repository를 삭제해줘야 하고 각각의 @Autowired도 제거해야 한다
* 이때 Controller는 삭제 하면 않된다*/

/*해당 방법의 장점은 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 때 좋다
* 예를 들어 DB를 변경해야 한다거나*/
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    private EntityManager em;

//    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }


}
