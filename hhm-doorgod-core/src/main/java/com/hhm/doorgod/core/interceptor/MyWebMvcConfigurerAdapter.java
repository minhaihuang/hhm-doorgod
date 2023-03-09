package com.hhm.doorgod.core.interceptor;

import com.hhm.doorgod.core.monitor.TimeStrategyMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author muyan.hwh
 * @date 2019-09-10
 */
@ComponentScan(basePackages = {"com.hhm.doorgod.core"})
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public HhmDoorGodInterceptor hhmDoorGodInterceptor() {
        return new HhmDoorGodInterceptor();
    }

    @Bean
    public TimeStrategyMonitor timeStrategyMonitor(){
        return new TimeStrategyMonitor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(hhmDoorGodInterceptor())
                .addPathPatterns("/**");
    }
}
