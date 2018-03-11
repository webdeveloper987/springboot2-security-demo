package com.springboot2.security.demosecurity.config;

import com.springboot2.security.demosecurity.error.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**",
                        "/home",
                        "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // .successHandler(successHandler())  -- works without this but doesnt with
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout") // default
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    /*@Bean
    public AuthenticationSuccessHandler successHandler()
    {
        return new MyCustomLoginSuccessHandler();
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}
