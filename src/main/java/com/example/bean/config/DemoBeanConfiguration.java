package com.example.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoBeanConfiguration {

    @Bean
    public String firstBean() {
        return "beanForBeanInjectionWithoutAutowiredNumberOne";
    }

    @Bean
    public String secondBean() {
        return "beanForBeanInjectionWithoutAutowiredNumberTwo";
    }

    @Bean
    public Integer firstBeanToBrakeAppByType() {
        return 1;
    }

    @Bean
    public Integer secondBeanToBrakeAppByType() {
        return 2;
    }
}
