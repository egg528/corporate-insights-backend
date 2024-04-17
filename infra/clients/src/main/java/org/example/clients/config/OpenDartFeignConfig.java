package org.example.clients.config;

import org.example.clients.interceptor.OpenDartInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenDartFeignConfig {

    @Value("${api.open-dart.key}")
    private String openDartKey;

    @Bean
    public OpenDartInterceptor openDartInterceptor() {
        return new OpenDartInterceptor(openDartKey);
    }
}
