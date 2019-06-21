package com.bangdao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@MapperScan(basePackages = {"com.bangdao.mapper","com.bangdao.tool.**.mapper"})
public class BangDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BangDaoApplication.class, args);
    }
}