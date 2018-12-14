package com.huisou;

import javax.servlet.MultipartConfigElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.common.BaseUtil;

/**
 * Created by miaorf on 2016/6/19.
 */
@EnableAutoConfiguration
@EnableAsync
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.huisou.mapper")
// @EnableJpaRepositories("com.huisou.repository")
@EnableTransactionManagement // 开启事务
// public class AppConfig{
public class AppConfig extends SpringBootServletInitializer {
    // private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private static Logger logger = LogManager.getLogger(AppConfig.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
        logger.info("SpringBoot Start Success");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的AppConfig启动类
        return builder.sources(AppConfig.class);
    }

    // 更改上传文件大小限制
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory(); // 允许上传的文件最大值
        factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }
}
