package com.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by David on 2019/10/14.
 */
@Configuration//代替XML文件，只需要在这个类上面加上@Configuration注解,
@ComponentScan//@ComponentScan注解就开启了自动扫描，如果注解没有写括号里面的东西,componentScan默认会扫描与配置类相同的包。
public class Book {
    private String title;
    private String price;
    public Book(){
        this.title = "标题";
        this.price = "100";
    }
    public Book(String title,String price){
        this.title = title;
        this.price = price;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
