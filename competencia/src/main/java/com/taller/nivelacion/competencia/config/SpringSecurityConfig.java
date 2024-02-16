package com.taller.nivelacion.competencia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/api/docs"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
        System.out.println("SpringSecurityConfig configure web");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Indica que no se utilizarán sesiones HTTP
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/usuario/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/usuario/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/competencia").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/usuario").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/competencia").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/api/v1/usuario/auth/login?logout")
                .permitAll()
                .and()
                .rememberMe()
                .key("uniqueAndSecret");
        System.out.println("SpringSecurityConfig configure httpSecurity");

    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
        System.out.println("SpringSecurityConfig configure auth");
    }

    @Bean("authenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        System.out.println("SpringSecurityConfig authenticationManager");
        return super.authenticationManager();

    }
}
