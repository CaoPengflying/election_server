package com.zzc.election_server.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @author caopengflying
 * @time 2019/1/23 13:46
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MybatisMapperConfiguration {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.zzc.election_server.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.zzc.election_server.util.TKMapper");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("notEmpty", "false");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
