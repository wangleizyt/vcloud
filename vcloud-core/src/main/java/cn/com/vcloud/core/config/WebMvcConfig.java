package cn.com.vcloud.core.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

//import cn.com.icloud.auth.core.interceptor.LogInterceptor;
/**
 * Spring MVC 配置
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
//	@Resource
//	LogInterceptor logInterceptor;
	
	
    /**
     * 阿里 FastJson 作JSON MessageConverter
     */
    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        final FastJsonConfig config = new FastJsonConfig();
        converter.setSupportedMediaTypes(new ArrayList<MediaType>() {{
            this.add(MediaType.APPLICATION_JSON_UTF8);
            this.add(MediaType.APPLICATION_FORM_URLENCODED);
            this.add(MediaType.TEXT_HTML);
        }});
        config.setSerializerFeatures(
                // 保留空的字段
                //SerializerFeature.WriteMapNullValue,
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//    	 // 日志记录
//        InterceptorRegistration logRegistration = registry.addInterceptor(logInterceptor);
//        logRegistration.addPathPatterns("/v1/**").excludePathPatterns("/", "/error");
//    }
}