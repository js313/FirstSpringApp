package com.sbear.firstapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    /*
    The throws Throwable clause in a method signature indicates that the method can throw any type of exception or error,
    including both checked exceptions and unchecked exceptions, as well as errors.

    Throwable
   ├── Exception
   │     ├── IOException
   │     └── SQLException
   │     └── (other exceptions...)
   └── Error
         ├── OutOfMemoryError
         ├── StackOverflowError
         └── (other errors...)
    Checked exceptions: These are exceptions that must either be caught or declared to be thrown. For example, IOException or SQLException.

    Unchecked exceptions: These are runtime exceptions (subclasses of RuntimeException), like NullPointerException or ArrayIndexOutOfBoundsException. You don't have to explicitly declare them or handle them.

    Errors: These are problems that typically cannot be recovered from (like OutOfMemoryError or StackOverflowError) and are not meant to be caught by the application.
     */
    @Around("execution(* com.sbear.firstapp..*.*(..))")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info(proceedingJoinPoint.getSignature().toString() + " method execution start");
//        Instant start = Instant.now();
        Object returnObj = proceedingJoinPoint.proceed();
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        log.info("Time took to execute " + proceedingJoinPoint.getSignature().toString() + " method is : "+timeElapsed);
//        log.info(proceedingJoinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.sbear.firstapp.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature() + " An exception happened due to : " + ex.getMessage());
    }
}
