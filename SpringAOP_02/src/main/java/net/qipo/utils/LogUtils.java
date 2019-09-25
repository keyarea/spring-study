package net.qipo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 如何将这个类(切面类)中的这些方法（通知方法）动态的在目标方法的各个位置切入
 */
@Aspect
@Component
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
     */

    // 想在目标方法运行之前运行，写切入点表达式
    // execution(访问权限符 返回值类型 方法签名)
    @Before("execution(public int net.qipo.impl.MyMathCalculator.*(int,int))")
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
    @AfterReturning(value = "execution(public int net.qipo.impl.MyMathCalculator.*(int,int))", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法正常执行完成了。他的计算结果是" + result);
    }

    /**
     * 告诉Spring这个exception是用来接受异常的 throwing="exception"
     */
    // 想在目标方法出现异常的时候执行
    @AfterThrowing(value = "execution(public int net.qipo.impl.MyMathCalculator.*(int,int))", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法出现异常了，异常信息是");
    }

    // 想在目标方法结束的时候执行
    @After("execution(public int net.qipo.impl.MyMathCalculator.*(int,int))")
    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【"+ name +"】方法结束了");
    }
}
