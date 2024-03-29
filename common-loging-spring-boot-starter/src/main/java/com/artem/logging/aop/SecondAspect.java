package com.artem.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class SecondAspect {

    @Around(value = "com.artem.logging.aop.FirstAspect.anyFindByIdServiceMethod()" +
            "&& args(id)" +
            "&& target(service)", argNames = "joinPoint,id,service")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object id, Object service) throws Throwable {
        log.info("AROUND before - invoked findById method in class {}, with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND returning - invoked findById method in class {}, result {}", service, result);
            return result;
        } catch (Throwable ex){
            log.info("AROUND throwing - invoked findById method in class {}, with exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("AROUND finally - invoked findById method in class {}", service);
        }
    }
}
