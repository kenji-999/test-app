package com.example.bean.service;

import com.example.bean.entity.DemoEntity;
import com.example.bean.repository.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {
    private final String firstBean;
    private final String secondBean;
    private final List<DemoEntity> demoEntities;

    //bean injection without autowired
    //bean injection by name
    //bean construct with no errors; no postConstruct is needed; bean configured by retrieving a data from db as expected
    public DemoService(final String firstBean,
                       final String secondBean,
                       final DemoRepository demoRepository) {
        this.firstBean = firstBean;
        this.secondBean = secondBean;
        this.demoEntities = demoRepository.findAll();
    }

    public String getFirstBeanValue() {
        return this.firstBean;
    }

    public String getSecondBeanValue() {
        return this.secondBean;
    }

    public List<DemoEntity> getDemoEntities() {
        return this.demoEntities;
    }
}
