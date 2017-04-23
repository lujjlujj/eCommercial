package com.ebuy.security;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by terry on 2017/4/23.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        HttpSessionCsrfTokenRepository securityCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        securityCsrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
        httpSecurity.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class)
                .headers()
                    .and()
                .authorizeRequests()
                    .antMatchers("/login.html", "/login", "/js/**", "/css/**")
                    .permitAll().anyRequest().authenticated()
                    .and()
                .formLogin()
                    .usernameParameter("username").passwordParameter("password")
                    .successHandler(new UrlAuthenticationSuccessHandler())
                    .failureHandler(new UrlAuthenticationFailureHandler())
                    .loginPage("/login").permitAll()
                    .and().
                logout()
                    .permitAll()
                    .and()
                .csrf()
                    .csrfTokenRepository(securityCsrfTokenRepository);//and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
