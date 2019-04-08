package cn.com.vcloud.core.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @className: ProjectConstant
 * @description: hibernate Validator参数校验
 * @author zhanghaifeng
 * @dateTime 2019年3月18日 
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        final MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        // 设置 validator 模式为快速失败返回
        postProcessor.setValidator(this.validatorFailFast());
        return postProcessor;
        // 默认是普通模式，会返回所有的验证不通过信息集合
        // return new MethodValidationPostProcessor();
    }

    @Bean
    public Validator validatorFailFast() {
        final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}