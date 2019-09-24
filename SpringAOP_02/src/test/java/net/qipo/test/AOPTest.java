package net.qipo.test;

import net.qipo.inter.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        // 1. 从ioc容器中拿到目标对象，注意，如果想要用类型，一定要使用他的接口类型，不要使用它的本类
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(1, 2);
    }
}
