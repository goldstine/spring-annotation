package org.goldstine.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //本类引用
    @Pointcut("execution(public int org.goldstine.aop.MathCalculator.div(int,int))")
    public void pointCut(){};

    //@Before在目标方法之前切入，切入点表达式（指定在那一个方法切入）
//    @Before("public int org.goldstine.aop.MathCalculator.*(..)")
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        //获取参数表
        Object[] args = joinPoint.getArgs();

        System.out.println(""+joinPoint.getSignature().getName()+""+"除法运行....参数列表是：{"+ Arrays.asList(args)+"}");
    }

//    @Before("public int org.goldstine.aop.MathCalculator.div(int,int)")
    @After("org.goldstine.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){

        System.out.println(""+joinPoint.getSignature().getName()+""+"除法结束....@After");
    }

//joinPoint形参必须写在前面
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(""+joinPoint.getSignature().getName()+""+"除法正常返回....运行结果:{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(""+joinPoint.getSignature().getName()+""+"除法异常....异常信息：{"+exception+"}");
    }
}
