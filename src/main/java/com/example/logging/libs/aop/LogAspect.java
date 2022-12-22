package com.example.logging.libs.aop;


import com.example.logging.libs.component.LogComponent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class LogAspect {
    private final LogComponent logComponent;

    @Pointcut(
            "within(@org.springframework.web.bind.annotation.RestController *) || " +
            "within(@org.springframework.stereotype.Service *) ||" +
            "within(@org.springframework.stereotype.Component *) ||" +
            "within(@org.springframework.stereotype.Repository *)")
    public void springBooAnnotationPointCut() {
    }


    @Pointcut("execution(public * com.example.logging.libs.aop.LogAspect(..)) || " +
            "execution(public * com.example.logging.libs.component.LogComponent(..))" )
    public void excludeClasses(){}
    @Before("springBooAnnotationPointCut() && !excludeClasses()")
    public void beforeMethodLog(JoinPoint joinPoint){
        logComponent.log(joinPoint);
    }

    @AfterThrowing(pointcut = "springBooAnnotationPointCut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logComponent.log(joinPoint, exception);
    }

    @AfterReturning(pointcut = "springBooAnnotationPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logComponent.log(joinPoint, result);
    }

}
