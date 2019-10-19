package com.conditionTest;

import com.condition.JdbcTemplateCondition;
import com.condition.LinuxCondition;
import com.condition.WindowsCondition;
import com.readinglist.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@Conditional({JdbcTemplateCondition.class})
public class BeanConfig {


    //正常注入一个Bean，没有加条件
    @Bean(name = "bill")
    public Person person1() {
        return new Person("Bill Gates", 62);
    }

    //添加条件配置接口
    @Conditional(WindowsCondition.class)//后面传入一个Class的数组，并且这个Class类需要继承Condition接口然后覆写matchs方法
    @Bean(name = "bill—Windows")
    public Person person3() {
        return new Person("Bill-Windows", 62);
    }

    @Bean("linus")
    public Person person2() {
        return new Person("Linus", 48);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus-Linux")
    public Person person24() {
        return new Person("Linus-Linux", 48);
    }

//    @Conditional({JdbcTemplateCondition.class})
//    @Bean("JdbcTemplate")
//    public Person personJdbcTemplate() {
//        return new Person("Jdbc", 48);
//    }
}