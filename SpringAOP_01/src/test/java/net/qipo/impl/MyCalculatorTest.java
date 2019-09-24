package net.qipo.impl;

import net.qipo.inter.Calculator;
import net.qipo.proxy.CalculatorProxy;
import org.junit.Test;


public class MyCalculatorTest {


    /**
     * 有了动态代理，日志记录可以做的非常强大，而且与业务逻辑解耦
     */
    @Test
    public void test01() {
        // jdk默认的动态代理，如果目标对象没有实现任何接口，是无法为它创建任何动态代理的。
        Calculator calculator = new MyCalculator();

        // 如果是拿到了这个对象的代理对象,代理对象执行加减乘除
        Calculator cal = CalculatorProxy.getProxy(calculator);
        // 代理对象与被代理对象唯一能产生关联的地方就是实现了同一个接口
        cal.add(1, 2);
        cal.div(1 ,0);
    }

}