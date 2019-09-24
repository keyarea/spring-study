package net.qipo.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtils {
    public static  void logStart(Method method, Object... args) {
        System.out.println("【" + method.getName() + "】方法开始执行了，用的参数列表【" + Arrays.asList(args) + "】");
    }

    public static void logReturn(Method method, Object result) {
        System.out.println("【" + method.getName() +"】方法正常执行完成了。他的计算结果是" + result);
    }

    public static void logException(Method method, Exception e) {
        System.out.println("【 " + method.getName() + " 】方法出现异常了，异常信息是" + e.getCause());
    }

    public static void logEnd(Method method) {
        System.out.println("【" + method.getName() + "】方法结束了");
    }
}
