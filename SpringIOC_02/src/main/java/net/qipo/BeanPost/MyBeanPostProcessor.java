package net.qipo.BeanPost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 1) 配置后置处理器的实现类;
 * 2) 将后置处理器配置在配置文件中;
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * postProcessBeforeInitialization:
     *   初始化之前调用
     * @param bean
     * @param beanName
     * @return 将要初始化的bean
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + beanName + "]bean将要调用初始化方法, bean: " + bean );

        // 返回传入的bean
        return bean;
    }

    /**
     * 初始化方法之后调用
     * @param bean
     * @param beanName bean在xml中配置的id
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + beanName + "]" +"初始化方法调用完毕了...");
        return bean;
    }
}
