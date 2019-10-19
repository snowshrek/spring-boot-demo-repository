package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//开启组件扫描和自动配置
//不仅是启动类，还是配置类
public class DemoSpringbootApplication {
	//负责启动引导应用程序(通过传递该类的一个引用，和命令行参数)
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootApplication.class, args);
		System.out.println("引导结束");
	}

	/*
	SpringBootApplication替代了三个注解的合并
	1.@Configuration 标明该类使用Spring基于XML或者Java（推荐）的配置
	2.@ComponentScan 启用组件扫描，自动发现并注册为Spring应用程序的上下文里的Bean
	3.@EnableAutoConfiguration 开启SpringBoot自动配置

	 不用显式的加载application.properties 只要它存在于resources里就会被SpringBoot自动加载
	   */

}
