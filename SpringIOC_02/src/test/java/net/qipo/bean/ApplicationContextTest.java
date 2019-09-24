package net.qipo.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;


public class ApplicationContextTest {
    private ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    /**
     * 单例: Bean的生命周期:
     *   构造器--> 初始化方法---> (容器关闭)销毁方法
     *
     * 多实例:
     *   获取Bean(构造器--->初始化方法)---> 容器关闭不会调用bean的销毁方法
     *
     * 后置处理器:
     *   (容器启动)构造器->后置处理器before--> 初始化方法--> 后置处理器after...-->bean初始化完成
     *
     *   无论bean是否有初始化方法;后置处理器都会默认其有,还会继续工作;
     */
    @Test
    public void test01() {
        // 单例
        System.out.println("容器要关闭了...");
        ioc.close();
    }

    @Test
    public void test02 () {
        // 多实例
        Object obj = ioc.getBean("book02");
        System.out.println("容器要关闭了");
        ioc.close();
    }


}