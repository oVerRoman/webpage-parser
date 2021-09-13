package com.github.webpage_statistician.domain.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingExceptionsAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingExceptionsAspect.class);

    @AfterThrowing(pointcut = "execution(* com.github.webpage_statistician.*.*.*(..))", throwing = "exception")
    public void after1RunStatisticianControllerExceptionThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        LOG.error("Method \"" + ((MethodSignature) joinPoint.getSignature()).getMethod() + "\" throws exception: "
                + exception + ".");
    }
}