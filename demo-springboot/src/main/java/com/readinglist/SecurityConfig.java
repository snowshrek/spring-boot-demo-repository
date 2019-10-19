package com.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * Created by David on 2019/10/19.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReaderRepository  readerRepository;
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);

    }
    /*
    下面这个configure指明请求路径对应的身份限制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().antMatchers("/").access("hasRole('READER')")
                                .antMatchers("/**").permitAll()
        .and().formLogin().loginPage("/login")
                .failureUrl("login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //这里的UserDetailsService可以换成任意一个实现了这个类的的其他服务类，用于查找指定用户名的用户
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                return null;
            }
        })
    }
}
