package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
/*
// JdbcTemplate 사용 시
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/

/*
// JPA 사용 시
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    // 이렇게 하면 스프링 데이터 JPA가 구현체를 만들어 놓은 것이 빈으로 등록이 되고 가져다 쓸 수 있음.
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*
    // TimeTraceAop 클래스에 직접 @Component 달아도 되지만 AOP 방식을 사용한다는 것을 명시하기 위해 빈으로 등록하는 것이 좋다.
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
*/

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

}
