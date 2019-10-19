package com.example.demospringboot;

import com.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Book.class)//通过SpringBoot加载上下文,将SpringBoot主类中导入的bean全都包含进来。此时SpringBoot主类也被当作了bean的收集器
public class DemoSpringbootApplicationTests {
//此时主类等同于 使用旧版XML注册bean 然后添加了@Configuration 和@ComponentScan注解的测试类
	@Test
	public void contextLoads() {//测试加载的上下文
		System.out.println("开始Demo测试");

	}
/*
注意@SpringApplicationConfiguration注解在1.4就被替换了,新版本这个注解已经不能用了
 */
}
