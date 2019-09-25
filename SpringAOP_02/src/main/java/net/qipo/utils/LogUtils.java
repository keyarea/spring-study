package net.qipo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 如何将这个类(切面类)中的这些方法（通知方法）动态的在目标方法的各个位置切入
 *
 * 多切面的顺序默认是按照切面函数的首字母顺序来排序的
 * 多切面时@Order可以改变切面顺序,数值越小优先级越高
 */
@Aspect
@Component
@Order(1)
public class LogUtils {

    /**
     * 告诉Spring，每个方法都什么时候运行
     * try｛
     *     @Before
     *     method.invoke(obj, args);
     *     @AfterReturning
     * }catch(Exception e) {
     *     @AfterThrowing
     * }finally{
     *     @After
     * }
     *
     * @Before: 在目标方法之前运行  前置通知
     * @After: 在目标方法结束之后   后置通知
     * @AfterReturning: 在目标方法正常返回之后    返回通知
     * @AfterThrowing： 在目标方法抛出异常之后运行  异常通知
     * @Around: 环绕  环绕通知
     *
     * Spring对通知方法的要求并不严格
     * 唯一的要求就是方法的参数列表一定不能乱写
     * 通知方法是Spring利用反射调用的，每次方法调用得确认这个方法的参数列表的值；
     * 参数表上的每一个参数，Spring都得知道是什么？
     * 不知道的参数一定要告诉Spring这是什么？
     *
     * 抽取可重用的切入点表达式：
     * 1、随便声明一个没有实现的返回void的空方法
     * 2、给方法上标注@Pointcut
     */

    @Pointcut("execution(public int net.qipo.impl.MyMathCalculator.*(int,int))")
    public void MyPoint(){};

    // 想在目标方法运行之前运行，写切入点表达式
    // execution(访问权限符 返回值类型 方法签名)
    @Before("MyPoint()")
    public static  void logStart(JoinPoint joinPoint) {
        // 获取到目标方法运行时使用的参数
        Object[] args = joinPoint.getArgs();
        // 获取到方法签名
        Signature signature = joinPoint.getSignature();
        // 拿到名字
        String name = signature.getName();
        System.out.println("【"+ name +"】方法开始执行了，用的参数列表【"+ Arrays.asList(args) +"】");
    }

    // 想在目标方法正常执行之后

    /**
     * 告诉Spring这个result用来接收返回值 returning="result"
     */
    @AfterReturning(value = "MyPoint()", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法正常执行完成了。他的计算结果是" + result);
    }

    /**
     * 告诉Spring这个exception是用来接受异常的 throwing="exception"
     */
    // 想在目标方法出现异常的时候执行
    @AfterThrowing(value = "MyPoint()", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法出现异常了，异常信息是");
    }

    // 想在目标方法结束的时候执行
    @After("MyPoint()")
    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法结束了");
    }

    /**
     * 环绕通知：@Around 是Spring中最强大的通知；
     * 动态代理
     * try｛
     *         @Before
     *         method.invoke(obj, args);
     *         @AfterReturning
     *    }catch(Exception e) {
     *         @AfterThrowing
     *     }finally{
     *         @After
     *   }
     * 四合一就是环绕通知
     *
     * 环绕通知中有一个参数: ProceedingJoinPoint
     * 环绕通知要优先于普通方法执行
     *
     * [普通前置方法]
     * {   环绕前置方法
     *     环绕执行目标方法
     *     环绕返回方法/异常
     *     环绕后置方法
     * }
     * [普通后置方法]
     * [普通返回方法\普通异常方法]
     *
     * 新的顺序: 环绕前置-->普通前置--->目标方法执行--->环绕正常返回/出现异常--->环绕后置--->普通后置--->普通返回或者后置
     */
    @Around("MyPoint()")
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
