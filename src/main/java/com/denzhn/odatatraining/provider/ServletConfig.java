package com.denzhn.odatatraining.provider;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<EntityServlet> exampleServletBean() {
        ServletRegistrationBean<EntityServlet> bean = new ServletRegistrationBean<>(
                new EntityServlet(), "/custom-provider/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
