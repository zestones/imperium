package com.imperium.imperium.security;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.imperium.imperium.service.user.UserService;

@Deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  UserService userDetailsService;

  @Autowired
  SecurityHandler handler;

  // ! PORT REDIRECTION CONFIG

  // private final int httpPort;
  // private final int httpsPort;

  // // Retrieve port value from application properties file
  // @Autowired
  // SecurityConfig(
  // @Value("${server.http.port}") int httpPort,
  // @Value("${server.https.port}") int httpsPort) {
  // this.httpPort = httpPort;
  // this.httpsPort = httpsPort;
  // }

  /**
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/index", "/css/**", "/js/**", "/img/**", "/img/icon/**", "/signIn",
            "/h2-console/**", "/api", "/api/**", "/home/profile/user/**")
        .permitAll()
        .antMatchers("/home", "/home/**", "/api/**").authenticated()
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
    // ! Custom failure handler
    // http.formLogin().failureHandler(handler);
    // ! Redirect http request to https
    // http
    // .portMapper().http(httpPort).mapsTo(httpsPort)
    // .and()
    // .requiresChannel().anyRequest().requiresSecure();

    // ! Allow access to the h2-console
    http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**");
    http.csrf().ignoringAntMatchers("/h2-console", "/h2-console/**", "/api/**", "/home/profile", "home/profile/**");
    http.cors().and().csrf().disable();
    http.headers().frameOptions().sameOrigin();
    http.authorizeRequests().anyRequest().denyAll();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  /**
   * @return AuthenticationManager
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authManager() throws Exception {
    return this.authenticationManager();
  }

  /**
   * @param auth : AuthenticationManagerBuilder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(userDetailsService.encoder);
  }
}