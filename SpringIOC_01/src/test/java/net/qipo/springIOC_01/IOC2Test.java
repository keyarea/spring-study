package net.qipo.springIOC_01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class IOC2Test {
    // 此时使用的是ioc2.xml的容器配置
    private ApplicationContext ioc = new  ClassPathXmlApplicationContext("ioc2.xml");

    /**
     * 测试为各种属性赋值
     * 测试使用null值,默认引用类型就是null值,基本类型就是默认值
     */
    @Test
    public void test01() {
        Person person = ioc.getBean("person01", Person.class);
        System.out.println(person.getName() == null); // true
        System.out.println(person.getCar());

        // 在配置文件中person01的car引用了id为car01的car,这是引用,而不是又创建了一个
        Car car01 = person.getCar();
        Car car02 = ioc.getBean("car01", Car.class);
        System.out.println(car01 == car02); // true
    }

    /**
     * 测试为car赋值,使用内部bean
     */
    @Test
    public void test02() {
        Person person = ioc.getBean("person02", Person.class);
        System.out.println(person.getCar());
    }

    /**
     * 测试如何为List赋值,books
     */
    @Test
    public void test03() {
        Person person = ioc.getBean("person03", Person.class);
        System.out.println(person.getBooks());
    }

    /**
     * 测试map赋值
     */
    @Test
    public void test04() {
        Person person = ioc.getBean("person04", Person.class);
        System.out.println(person.getMaps());
    }

    /**
     * 为properties赋值
     */
    @Test
    public void test05() {
        Person person = ioc.getBean("person05", Person.class);
        System.out.println(person.getProperties());
    }

    /**
     * 使用util命名空间创建
     */
    @Test
    public void test06() {
        Person person = ioc.getBean("person06", Person.class);
        System.out.println(person.getMaps());

        Map maps = (Map) ioc.getBean("myMap");
        System.out.println(maps.getClass());
    }

    /**
     * 级联属性可以修改属性的属性,注意原来的bean可能会被修改
     */
    @Test
    public void test07() {
        Person person = ioc.getBean("person07", Person.class);
        Car car = ioc.getBean("car01", Car.class);
        System.out.println(person.getCar());
        System.out.println(car);
    }

    @Test
    public void test08() {
        Person person = ioc.getBean("person09", Person.class);
        System.out.println("person09: " + person);
    }
}
