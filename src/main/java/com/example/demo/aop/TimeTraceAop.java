package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    @Around("execution(* com.example.demo..*(..)") //com.exmaple.demo 패키지 하위에 있는 것에 다 적용해라
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.currentTimeMillis();
        System.out.println("Start : "+joinPoint.toString());
        try{
            Object result=joinPoint.proceed();  //다음 메소드로 진행된다
            return result;
        }finally{
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("Finish :"+joinPoint.toString() + timeMs + "ms" );
        }

    }
}
