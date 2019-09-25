package net.qipo.test;

import net.qipo.impl.MyMathCalculator;
import net.qipo.inter.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        // 1. 从ioc容器中拿到目标对象，注意，如果想要用类型，一定要使用他的接口类型，不要使用它的本类
//        Calculator calculator = context.getBean(Calculator.class);
//        calculator.add(1, 2);


        // 如果目标对象没有接口，那么就是本类类型
        // cglib帮我们创建好了代理对象
        MyMathCalculator bean = context.getBean(MyMathCalculator.class);
        bean.add(1, 2);
    }

    /**
     * 通知方法的执行顺序
     *
     * 正常执行: @Before(前置通知) ==》 @After（后置通知） ===》 @AfterReturning（返回通知）
     * 异常执行: : @Before(前置通知) ==》 @After（后置通知） ===》@AfterThrowing(异常通知)
     */
    @Test
    public void test02() {

        MyMathCalculator bean = context.getBean(MyMathCalculator.class);
        bean.div(1, 2);
        bean.div(1, 0);

    }
}


