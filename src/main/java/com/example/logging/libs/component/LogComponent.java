package com.example.logging.libs.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class LogComponent {
    public void log(JoinPoint joinPoint) {
        log.info("Called method: {}, Class Name: {}, Arguments: {}, Target class: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass().getName());
    }

    public void log(JoinPoint joinPoint, Object result) {
        log.info("Returning Value: {} from Method: {}, Class Name: {}, Arguments: {}, Target class: {}",
                getResult(result),
                joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass().getName());
    }

    public void log(JoinPoint joinPoint, Throwable exception) {
        Signature signature = joinPoint.getSignature();
        Throwable throwable = exception.getCause();
        log.error("Exception in {}.{}() with cause = {}",
                signature.getDeclaringTypeName(),
                signature.getName(),
                throwable != null ? throwable : "NULL");
    }

    private String getResult(Object result) {
        return result == null ? "null" : result.toString();
    }

}
