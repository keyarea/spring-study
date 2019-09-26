package net.qipo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class LogUtils {

    public static  void logStart(JoinPoint joinPoint) {
        // 获取到目标方法运行时使用的参数
        Object[] args = joinPoint.getArgs();
        // 获取到方法签名
        Signature signature = joinPoint.getSignature();
        // 拿到名字
        String name = signature.getName();
        System.out.println("【"+ name +"】方法开始执行了，用的参数列表【"+ Arrays.asList(args) +"】");
    }

    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法正常执行完成了。他的计算结果是" + result);
    }

    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法出现异常了，异常信息是");
    }

    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法结束了");
    }

    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 方法名
        String name = proceedingJoinPoint.getSignature().getName();
        Object proceed = null;
        try {
            // 就是利用反射调用目标方法即可, 就是反射中的method.invoke(obj,args);
            // @Before
            System.out.println("【环绕的前置通知】" + name + "方法开始执行");
            proceed = proceedingJoinPoint.proceed(args);
            // @AfterReturning
            System.out.println("【环绕的返回通知】" + name + "方法返回，返回值" + proceed);
        }catch (Exception e) {
            // @AfterThrowing
            System.out.println("【环绕的异常通知】"+ name + "方法异常，异常信息" + e);
            // 为了能让外界感知到这个异常,这个异常一定要抛出去
            throw new RuntimeException(e);
        }finally {
            // @After
            System.out.println("【环绕的后置通知】" + name + "方法结束");
        }

        // 反射调用后的返回值也一定要返回出去
        return proceed;
    }
}
