package com.contribuidor.cma.util.pagination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class RxPaginateAdapter extends WebMvcConfigurerAdapter {

    @Value("${size.max.page}")
    private int size;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver>argumentResolvers){
        PageableHandlerMethodArgumentResolver pageble = new PageableHandlerMethodArgumentResolver();
        pageble.setFallbackPageable(new PageRequest(0,size));
        argumentResolvers.add(pageble);
    }
}
