package com.mycompany.carmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.interceptor.ThymeleafLayoutInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/slimtable/**").addResourceLocations("classpath:/static/slimtable/");
		registry.addResourceHandler("/jtableResources/**").addResourceLocations("classpath:/static/jtableResources/");
    }
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
//    	registry.addConverter(carConverter());
//    	registry.addConverter(employeeConverter());
//    	registry.addConverter(providerConverter());
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @Bean
    public StringToEntityConverter carConverter() {
        return new StringToEntityConverter(Car.class);
    }
    @Bean
    public StringToEntityConverter employeeConverter() {
        return new StringToEntityConverter(Employee.class);
    }
    @Bean
    public StringToEntityConverter providerConverter() {
        return new StringToEntityConverter(Provider.class);
    }
}
