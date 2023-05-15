package com.dgomezt.practicaintegradora.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    @Bean
    //Define un interceptor (listener) nombre localChangeInterceptor
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    @Bean
    //Define un interceptor (listener) nombre localChangeInterceptor
    public LocaleChangeInterceptor localeChangeCSSInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("css");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Añade el interceptor creado anter al registro
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(localeChangeCSSInterceptor());
    }
}
