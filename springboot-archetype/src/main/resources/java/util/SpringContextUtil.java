#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author XanderYe
 * @description:
 * @date 2021/12/7 14:10
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 开发配置
     */
    public static final String DEV_PROFILE = "dev";
    /**
     * 测试配置
     */
    public static final String TEST_PROFILE = "test";
    /**
     * 生产配置
     */
    public static final String PROD_PROFILE = "pro";

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @param 
     * @return org.springframework.context.ApplicationContext
     * @author XanderYe
     * @date 2021/12/7
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取Bean
     * @param name
     * @return T
     * @author XanderYe
     * @date 2021/12/7
     */
    public static Object getBean(String name) {
        Object o = null;
        try {
            o = getApplicationContext().getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            // e.printStackTrace();
        }
        return o;
    }

    /**
     * 获取Bean
     * @param clazz
     * @return T
     * @author XanderYe
     * @date 2021/12/7
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据名称获取指定Bean
     * @param name
     * @param clazz
     * @return T
     * @author XanderYe
     * @date 2021/12/7
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取Bean的类型
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 获取配置文件配置项的值
     * @param
     * @return java.lang.String
     * @author XanderYe
     * @date 2021/12/7
     */
    public static String getEnvironmentProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }

    /**
     * 获取spring.profiles.active
     * @param
     * @return java.lang.String
     * @author XanderYe
     * @date 2021/12/7
     */
    public static String getActiveProfile() {
        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
    }
}
