package com.cbs.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class ResourcesConfigAdapter
        extends WebMvcConfigurerAdapter {
 
    @Value("${preread.uploadPath}")
    private String resourceDir;
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadPath/**").addResourceLocations("file:"+resourceDir);
        super.addResourceHandlers(registry);
    }
 
}