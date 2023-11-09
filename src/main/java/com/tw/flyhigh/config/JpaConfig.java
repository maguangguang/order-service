package com.tw.flyhigh.config;

import com.tw.flyhigh.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.tw.flyhigh.repository"}, repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class JpaConfig {
}
