package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 스프링 빈에 등록 -> SpringConfig에 timeTraceAop
// @Component으로 스프링 빈에 등록해도 됨
@Aspect // AOP 로 쓰기 위해 등록
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // hellospring 패키지 하위에 전부 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // 변수 인라인 화 (Ctrl + Shift + Alt + T)
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() +" " + timeMs + "ms");
        }
    }
}
