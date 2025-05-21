package tn.esprit.examen.nomPrenomClasseExamen.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @AfterReturning(
            pointcut = "execution(* tn.esprit.examen.nomPrenomClasseExamen.services.*.compensationMilles(..))",
            returning = "result"
    )
    public void logAfterSuccessfulAddMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Passangers compenses !!!");
    }


//    @Pointcut("execution (* tn.esprit.examen.nomPrenomClasseExamen.services.*.*(..))")
//    public void methodCall() {}
//
//    @Before("methodCall()")
//    public void methodEntry(JoinPoint joinPoint){
//
//        log.info("In Method : "+joinPoint.getSignature().getName());
//    }
//    @After("methodCall()")
//    public void outOfMethod(JoinPoint joinPoint){
//        log.info("Out of Method : "+joinPoint.getSignature().getName());
//    }
//    @AfterReturning("methodCall()")
//    public void logMethodExitReturn(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        log.info("AfterReturning " + name + " avec succès ! ");
//    }
//
//    @AfterThrowing(pointcut="methodCall()", throwing="nameEx")
//    public void logMethodExitThrowing(JoinPoint joinPoint, Throwable nameEx) {
//        String name = joinPoint.getSignature().getName();
//        log.info("AfterThrowing of method " + name + " : ");
//        log.error(nameEx.getMessage());
//    }
}
