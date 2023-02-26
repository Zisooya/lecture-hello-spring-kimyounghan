package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    // @Around: 공통 관심사를 어디에 적용할 지 타겟팅 할 수 있는 애노테이션.
    // hello.hellospring 패키지 하위에 다 적용.
    @Around("execution(* hello.hellospring..*(..))")
    public  Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // proceed(): 다음 메서드로 진행.
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() +" " + timeMs + "ms");
        }
    }
}
