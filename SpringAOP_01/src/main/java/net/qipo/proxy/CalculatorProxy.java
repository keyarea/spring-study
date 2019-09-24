package net.qipo.proxy;

import net.qipo.inter.Calculator;
import net.qipo.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 帮助Calculator类生成代理对象的类
 * Object newProxyInstance
 */
public class CalculatorProxy {
    /**
     * 为传入的参数创建一个动态代理对象
     * @param calculator
     * @return
     *
     * Calculator calculator : 被代理对象
     */
    public static Calculator getProxy(Calculator calculator) {
        ClassLoader loader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();

        // 方法执行器，帮我们目标对象执行目标方法
        InvocationHandler h = new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象，给jdk使用，任何时候都不要动这个对象
             * @param method 当前将要执行的目标对象的方法
             * @param args 这个方法调用的时候外界传入的参数值
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 利用反射执行目标方法
                // 目标方法执行后的返回值
                Object result = null;
                try{
                    LogUtils.logStart(method, args);
                    result = method.invoke(calculator, args);
                    LogUtils.logReturn(method, result);
                }catch (Exception e) {
                    LogUtils.logException(method, e);
//                    e.printStackTrace();
                }finally {
                    LogUtils.logEnd(method);

                }
                // 返回值必须返回出去外界才能拿到整整执行后的返回值
                return result;
            }
        };


        // Proxy为目标对象创建代理对象
        Object o = Proxy.newProxyInstance(loader, interfaces, h);
        return (Calculator) o;
    }
}
