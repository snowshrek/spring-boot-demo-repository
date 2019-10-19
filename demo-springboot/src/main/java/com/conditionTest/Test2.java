package com.conditionTest;

import com.readinglist.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by David on 2019/10/17.
 */

public class Test2 {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

//    @Test
    public void test1(){
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
//        String osName = applicationContext.getEnvironment().getProperty("os.name");
//        System.out.println("当前系统为：" + osName);
//        Map<String, Book> map = applicationContext.getBeansOfType(Book.class);
//        System.out.println(map);

    }
}
