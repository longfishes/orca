package com.longfish.orca.config;

import com.longfish.orca.interceptor.AccessLimitInterceptor;
import com.longfish.orca.interceptor.JwtTokenInterceptor;
import com.longfish.orca.interceptor.MobileCurrentIdInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Autowired
    private MobileCurrentIdInterceptor mobileCurrentIdInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors...");

        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/m/**")
                .excludePathPatterns("/user/forgot")
                .excludePathPatterns("/user/lambdaLogin")
                .excludePathPatterns("/user/lambdaCodeLogin")
                .excludePathPatterns("/user/uniqueCheck")
                .excludePathPatterns("/user/code")
                .excludePathPatterns("/user/lambdaRegister")
                .excludePathPatterns("/user/login");

        registry.addInterceptor(accessLimitInterceptor);

        registry.addInterceptor(mobileCurrentIdInterceptor)
                .addPathPatterns("/m/**")
                .excludePathPatterns("/m/user/lambdaLogin")
                .excludePathPatterns("/m/user/lambdaCodeLogin")
                .excludePathPatterns("/m/user/uniqueCheck")
                .excludePathPatterns("/m/user/code")
                .excludePathPatterns("/m/user/lambdaRegister")
                .excludePathPatterns("/m/user/forgot")
                .excludePathPatterns("/m/user/login");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
