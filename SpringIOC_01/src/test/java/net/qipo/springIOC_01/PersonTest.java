package net.qipo.springIOC_01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class PersonTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
    /**
     * 从容器中拿到这个组件
     */
    @Test
    public void test() {
        // ApplicationContext: 代表着ioc容器
        // ClassPathXmlApplicationContext: 当前应用的xml配置文件在classPath下
        // 根据spring的配置文件得到ioc容器对象
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");

        // 容器帮我们创建好对象了
        Person person = (Person) ioc.getBean("person01");

        System.out.println(person);
    }

    /**
     * 根据bean类型从IOC容器中获取bean实例
     */
    @Test
    public void test01() {
//        Person bean = ioc.getBean(Person.class);
////        如果bean类型在xml配置中不止一个,就会报错: NoUniqueBeanDefinitionException
//        System.out.println(bean);

        Person person = ioc.getBean("person01", Person.class);

        System.out.println(person);
    }

    /**
     * 调用有参构造器创建对象并赋值
     */
    @Test
    public void test03() {
        Person person = ioc.getBean("person03", Person.class);
        System.out.println(person);
    }

    /**
     * 调用有参构造器,按顺序赋值
     */
    @Test
    public void test04() {
        Person person = ioc.getBean("person04", Person.class);
        System.out.println(person);
    }

    /**
     * 有参构造,重载的情况下xml中可以指定参数的类型
     */
    @Test
    public void test05() {
        Person person = ioc.getBean("person05", Person.class);
        System.out.println(person);
    }

    /**
     * p名称空间赋值,调用的还是setter方法
     */
    @Test
    public void test06() {
        Person person = ioc.getBean("person06", Person.class);
        System.out.println(person);
    }
}