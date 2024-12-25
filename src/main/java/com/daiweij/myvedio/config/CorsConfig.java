package com.daiweij.myvedio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")  // 配置允许跨域的路径
                .allowedOrigins("http://localhost:8000")  // 允许来自指定来源的请求，前端地址（假设是 http://localhost:8000）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true)  // 是否允许携带认证信息（如 cookie）
                .maxAge(3600);  // 预检请求的缓存时间，单位为秒
    }
}
