package com.doctorAppointmentBookingSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Edi on 04-May-17.
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    //@Value("${upload.location}")
    private static final String PICTURE_RESOURCE_HANDLER = "/mm_pics/**";
    private static final String PICTURE_RESOURCE_LOCATION = "file:C:/dabs_mm_pics/doctor_pic/";

    private static final String[] RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }

        registry.addResourceHandler(PICTURE_RESOURCE_HANDLER).addResourceLocations(PICTURE_RESOURCE_LOCATION);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);

        configurer.setUseSuffixPatternMatch(false);
    }
}
