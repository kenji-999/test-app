package com.example.bean.service;

import lombok.RequiredArgsConstructor;

//uncomment to break an app and receive NoUniqueBeanDefinitionException
//@Service
@RequiredArgsConstructor
public class ApplicationStartupBreakerService {
    private final Integer integerBean;
}
