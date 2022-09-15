package com.artem.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Slf4j
public class FirstAspect {

    /*
        execution(modifiers pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
    @Pointcut("execution(public * com.artem.*.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod() " +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy)" +
            "&& @within(transactional)", argNames = "joinPoint,id,service,serviceProxy,transactional")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("before - invoked findById method in class {}, with id {}", service, id);
    }


    @AfterReturning(value = "anyFindByIdServiceMethod()" +
            "&& target(service)", returning = "result", argNames = "result,service")
    public void addLoggingAfterFinally(Object result, Object service) {
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod()" +
            "&& target(service)", throwing = "exp", argNames = "service,exp")
    public void addLoggingAfterThrowing(Object service, Throwable exp) {
        log.info("after throwing - invoked findById method in class {}, with exception {}: {}", service, exp.getClass(), exp.getMessage());
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after finally - invoked findById method in class {}", service);
    }




}
