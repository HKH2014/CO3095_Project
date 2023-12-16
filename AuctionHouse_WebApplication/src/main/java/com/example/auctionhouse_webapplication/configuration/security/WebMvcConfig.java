package com.example.auctionhouse_webapplication.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());

        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");

        return viewResolver;
    }

    //    @Bean
    //    public MessageSource messageSource() {
    //        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
    //        bean.setBasename("classpath:messages");
    //        bean.setDefaultEncoding("UTF-8");
    //        return bean;
    //    }
    //
    //    @Bean
    //    public LocalValidatorFactoryBean validator() {
    //        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    //        bean.setValidationMessageSource(messageSource());
    //        return bean;
    //    }
    //
    //    @Override
    //    public Validator getValidator() {
    //        return validator();
    //    }
    //@Autowired
    //private ServletContext servletContext;
    //@Autowired
    //private ThymeleafViewResolver thymeleafViewResolver;
    //@Bean
    //@Description("Thymeleaf template resolver serving HTML5")
    //public ClassLoaderTemplateResolver templateResolver() {
    //    ClassLoaderTemplateResolver servletContextTemplateResolver = new ClassLoaderTemplateResolver();
    //
    //    servletContextTemplateResolver.setPrefix("src/main/resources/templates/");
    //    servletContextTemplateResolver.setCacheable(false);
    //    servletContextTemplateResolver.setSuffix(".html");
    //    servletContextTemplateResolver.setTemplateMode("HTML");
    //    servletContextTemplateResolver.setCharacterEncoding("UTF-8");
    //
    //    return servletContextTemplateResolver;
    //}
    //@Bean
    //@Description("Thymeleaf template engine with Spring integration")
    //public SpringTemplateEngine templateEngine() {
    //    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
    //
    //    springTemplateEngine.setTemplateResolver(templateResolver());
    //
    //    return springTemplateEngine;
    //}
    //@Bean
    //@Description("Thymeleaf view resolver")
    //public ViewResolver viewResolver() {
    //    ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
    //
    //
    //    thymeleafViewResolver.setCharacterEncoding("UTF-8");
    //
    //    return thymeleafViewResolver;
    //}

}

