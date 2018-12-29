package com.example.zkconfig.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //把自己从写的DataSource交给spring管理
    @Bean
    public DataSource dataSource(){
        EnjoyDataSource dataSource=new EnjoyDataSource();
        return  dataSource;
    }
}
