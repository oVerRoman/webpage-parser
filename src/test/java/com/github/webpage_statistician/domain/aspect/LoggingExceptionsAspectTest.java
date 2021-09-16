package com.github.webpage_statistician.domain.aspect;

import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.github.webpage_statistician.dao.DAOFileReader;
import com.github.webpage_statistician.dao.DAOReader;

class LoggingExceptionsAspectTest {

    LoggingExceptionsAspect aspect;
    @Mock
    private JoinPoint joinPoint;
    @Mock
    MethodSignature methodSignature;
    Method method;
    DAOFileReader reader;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loggingExceptionsAspectTest_checkExecution_whenAspectCalled() throws IOException {
        reader = new DAOFileReader();
        aspect = new LoggingExceptionsAspect();
        Throwable thrown = new IOException();
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        aspect.afterRunStatisticianControllerExceptionThrowingAdvice(joinPoint, thrown);
        verify(joinPoint, times(1)).getSignature();
    }

    @Test
    void loggingExceptionsAspectTest_throwException_whenReaderCanNotFindFile() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new DAOFileReader());
        factory.addAspect(new LoggingExceptionsAspect());
        DAOReader proxy = factory.getProxy();
        try {
            proxy.readText("src/test/resources/nonexistenttestreadingfile.txt");
            shouldHaveThrown(IOException.class);
        } catch (IOException ioe) {
        }
    }
}