#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import ${package}.filter.CorsFilter;
import ${package}.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author XanderYe
 * @date 2018-12-21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Value("${symbol_dollar}{static.path.pattern}")
    private String staticPathPattern;
    @Value("${symbol_dollar}{static.resources.locations}")
    private String staticResourcesLocations;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginInterceptor).addPathPatterns("/**")
                // swagger页面
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/v2/api-docs-ext")
                // swagger 静态资源
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/upload/**")
                // 登录注销
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/logout");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPathPattern.split(",")).addResourceLocations(staticResourcesLocations.split(","));
    }

    /**
     * 过滤器
     * @param
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @author XanderYe
     * @date 2019/8/27
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CorsFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        /**
         * 1.先定义一个convert转换消息的对象
         * 2.添加fastjson的配置信息，比如：是否要格式化返回的json数据
         * 3.在convert中添加配置信息
         * 4.将convert添加到converters当中
         */
        //1.先定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);

        //处理中文乱码问题(不然出现中文乱码)
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastConverter);
    }
}
