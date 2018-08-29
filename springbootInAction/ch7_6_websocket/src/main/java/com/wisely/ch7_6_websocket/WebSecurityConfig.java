package com.wisely.ch7_6_websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Create by haifei on 29/8/2018 ${time}.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/", "/login").permitAll() //根路径与／login路径不拦截
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")  //登陆页面
      .defaultSuccessUrl("/chat") //成功登陆跳转页面
      .permitAll()
      .and()
      .logout()
      .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .passwordEncoder(NoOpPasswordEncoder.getInstance())
      .withUser("123").password("123").roles("USER")
      .and()
      .withUser("456").password("456").roles("USER");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/static/**");
  }
}
