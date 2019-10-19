安装SpringBootCli



### SpringBoot 基础使用

SpringBoot新建项目以后Pom文件自动生成，Maven提示标红就是说找不到这个jar包，原因无非名字写错或者maven仓库内没有这个jar包。pom自动生成所以名字没有问题，那原因一定就是maven本地仓库内没有这jar，只需要maven install一下把jar包下载下来即可（保证maven和镜像库设置没有问题），下载完成后重新maven→reimport重新导入就可以了

ps:如果本地没有这个jar包一般会报错Cannot resolve symbol 'SpringBootApplication

依赖添加正确后，maven install操作，然后检查本地的类有没有下载下来,就是在自己设置的仓库里检查有没有相应的jar包

检查maven镜像库时注意是否使用自己的私有库，例如

```
<repository>    
<id>releases</id>    <url>http://183.129.141.106:8081/nexus/content/repositories/releases</url>
</repository>
```

pom文件里有类似的设置就是使用自身的私有库，库的用户名密码一般再maven程序目录的settins.xml文件中设置，例如

```
	<servers>
		<server>
			<id>releases</id>
			<username>admin</username>
			<password>XXXXXXX</password>
		</server>
	</servers>
```

spring boot的pom文件中设置起步依赖时不需要指定版本号，起步依赖本身的版本是由正在使用的spring boot的版本决定的，Spring boot经过足够的测试保证引入的全部依赖能够相互兼容

如果本着为项目瘦身的原则去掉用不上的依赖，可以使用\<exclusions\>元素

例如从web依赖里去掉jackson

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
		<groupId>com.fasterxml.jackson.core</groupId>
		</exclusion>
	</exclusions>
</dependency>
```

如果是要使用指定的版本，使用 \<version\> 指定版本，即pom里指定的依赖会覆盖传递依赖引入的默认值



### Spring Boot的自动配置

原理：Spring的条件化配置接口Condition 和注解@Conditional，该注解的作用是按照一定的条件进行判断，满足条件给容器注册bean

@Conditional注解的定义如下

```
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface Conditional {
    Class<? extends Condition>[] value();
}
```

1. @Target表示使用范围： 类,接口或枚举(TYPE)   方法(METHOD)

2. @Retention该元注解表明生命周期： 整个运行期，即保存到class文件中，jvm加载

   class文件之后，仍然存在

3. @Documented注解表明这个注释是由 javadoc记录的，在默认情况下也有类似的记录工具。 如果一个类型声明被注释了文档化，它的注释成为公共API的一部分

4. 使用该注解，后面传入一个Class的数组，并且这些Class类需要继承Condition接口，例如@Conditional({XXXXX.Class})



首先自定义接口实现org.springframework.context.annotation.Condition接口，并覆写该接口的boolean matches(ConditionContext var1, AnnotatedTypeMetadata var2)方法，该方法返回一个布尔值，返回true时表示满足条件给容器注册bean注入Bean，false不生效



##### 注解使用范围的举例

用在方法上：（一个方法只能注入一个bean实例）（数组里只要䘝class的时候可以去掉大括号）

```
	@Conditional({LinuxCondition.class})
    @Bean("linus-Linux")
    public Person person24() {
        return new Person("Linus-Linux", 48);
    }
```

用在类上：（批量注入很多个实例）

```
@Conditional({JdbcTemplateCondition.class})
public class BeanConfig {
```

在类上使用，如果条件接口返回false，则该类里提供的配置都会被忽略掉



SpringBoot的自动配置就是基于spring的条件化配置，SpringBoot定义好了许多特殊的条件化注解。

例如SpringBoot定义的：@ConditionalOnClass()表示路径下有指定的类，则注入生效，对应的SpringBoot的用到配置例如:DataSourceAutoConfiguration

```
@Configuration
@ConditionalOnClass({javax.sql.DataSource.class,EmbeddedDatabaseType.class})
@EnableConfigurationProperties({DataSourceProperties.class})
@Import({org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration.class, DataSourceInitializationConfiguration.class})
public class DataSourceAutoConfiguration{}
```

里面的@ConditionalOnClass后面就表示必须有DataSource和EmbeddedDatabaseType,如果他们不存在，那整个后面DataSourceAutoConfiguration提供的配置都不生效

结合之前写的简单的demo例子，**初始化SpringBoot项目时，选择了H2，JPA,Thymeleaf，SpringMVC，Tomcat，**

**则自动配置spring-boot-autoconfigure-2.1.9.RELEASE这个jar里的以下的这些配置就会生效**，并提供以下功能：

1. 创建一个嵌入式的H2数据库Bean,
2. 配置与hibernate相关的Bean,包括Spring的LocalConainerEntityManager-FactoryBean等
3. Thymeleaf会配置为SpringMVC的视图，包括Thymeleaf体系相关的模板解析器，模板引擎和视图解析器
4. 配置Spring的DispatcherServlet并启用SpringMVC
5. 启动一个嵌入式的Tomcat容器，监听端口8080   （Web起步依赖传递引用）



### 自定义配置

举例：起步依赖添加Spring的安全模块

```

```

