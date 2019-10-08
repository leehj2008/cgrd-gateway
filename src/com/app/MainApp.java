package com.app;


import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by Administrator on 2016/5/6.
 */
@ImportResource("beans.xml")
@ComponentScan(value = {"com.app"})  
@PropertySources(value={@PropertySource("ftp.properties"),@PropertySource("config.properties")})
@EnableAutoConfiguration 
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,MybatisAutoConfiguration.class})
public class MainApp extends SpringBootServletInitializer {
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApp.class);
    }

    public static void main(String[] args) throws Exception {
    	
        MainApp app = new MainApp();
        app.startServer();
    }

	private void startServer() {
		//start main service of springboot
		SpringApplication.run(MainApp.class);

	}

}