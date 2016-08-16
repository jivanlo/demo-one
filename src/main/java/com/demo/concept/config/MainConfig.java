package com.demo.concept.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created by admin on 8/8/16.
 */
@Configuration
@PropertySource({ "classpath:database.properties", "classpath:application.properties" })
@ComponentScan(basePackages = "com.demo.concept")
@EnableScheduling
public class MainConfig
{

}
