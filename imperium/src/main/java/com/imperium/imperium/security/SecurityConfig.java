package com.imperium.imperium.security;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.imperium.imperium.service.user.UserService;

@Deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  UserService userDetailsService;

  @Inject
  SecurityHandler handler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/index", "/css/**", "/img/**", "/img/icon/**", "/signIn",
            "/h2-console/**")
        .permitAll()
        .antMatchers("/home", "/home/**").authenticated()
        .and()
        .formLogin()
        .loginPage("/logIn")
        .loginProcessingUrl("/process-logIn")
        .failureUrl("/logIn?error=true")
        .defaultSuccessUrl("/home")
        .permitAll();
    http.formLogin()
        .and().logout()
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
    // * Custom success handler
    http.formLogin()
        .successHandler(handler);
    // * Custom failure handler
    http.formLogin()
        .failureHandler(handler);

    // ! Allow access to the h2-console
    http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**");
    http.csrf().ignoringAntMatchers("/h2-console", "/h2-console/**");
    http.headers().frameOptions().sameOrigin();

    http.authorizeRequests().anyRequest().denyAll();
  }

  @Bean
  public AuthenticationManager authManager() throws Exception {
    return this.authenticationManager();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.encoder);
  }
}