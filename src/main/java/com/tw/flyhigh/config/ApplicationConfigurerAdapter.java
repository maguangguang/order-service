package com.tw.flyhigh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class ApplicationConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean("mappingJackson2HttpMessageConverter")
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return jsonConverter;
    }

    @Bean
    public FilterRegistrationBean<CocoAuthenticationFilter> authFilter() {
        FilterRegistrationBean<CocoAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(cocoAuthenticationFilter());
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }

    @Bean
    public CocoAuthenticationFilter cocoAuthenticationFilter() {
        return new CocoAuthenticationFilter();
    }

    static class CocoAuthenticationFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String url = httpServletRequest.getRequestURI();

            setCORS(httpServletResponse);

            if ("OPTIONS".equals(httpServletRequest.getMethod())) {
                chain.doFilter(request, response);
                return;
            }

            log.info("request uri : " + httpServletRequest.getRequestURI());

            if (url.contains("swagger") || url.contains("/api-docs")) {
                chain.doFilter(request, response);
                return;
            }
            chain.doFilter(request, httpServletResponse);
        }

        private void setCORS(HttpServletResponse httpServletResponse) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Max-Methods", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        }
    }
}
