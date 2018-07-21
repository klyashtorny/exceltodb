package com.kliashtorny.exceltodb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Anton Klyashtorny
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.kliashtorny")
public class ExcelAppConfiguration extends WebMvcConfigurerAdapter {

}
