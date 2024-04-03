package com.nivelacion.taller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nivelacion.taller.filter.CustomAthenticationFilter;
import com.nivelacion.taller.filter.CustomAthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // CustomAthenticationFilter customAthenticationFilter = new
    // CustomAthenticationFilter(authenticationManager());
    // customAthenticationFilter.setFilterProcessesUrl("/api/v1/login");
    // http.csrf().disable();
    // http.sessionManagement().sessionCreationPolicy(STATELESS);
    // http.authorizeRequests().antMatchers("/api/v1/login/**",
    // "/api/v1/token/refresh/**").permitAll();
    // http.authorizeRequests().antMatchers(HttpMethod.GET,
    // "/api/v1/usuario/**").hasAnyAuthority("ROLE_ADMIN");
    // // http.authorizeRequests().antMatchers(HttpMethod.POST,
    // // "/api/v1/usuario/save/**").hasAnyAuthority("ROLE_ADMIN",
    // // "ROLE_USER");
    // http.authorizeRequests().anyRequest().authenticated();
    // http.addFilter(customAthenticationFilter);
    // http.addFilterBefore(new CustomAthorizationFilter(),
    // UsernamePasswordAuthenticationFilter.class);
    // }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAthenticationFilter customAthenticationFilter = new CustomAthenticationFilter(authenticationManager());
        customAthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/login/**", "/api/v1/token/refresh/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/usuario/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/usuario/save").permitAll() // Permitir acceso sin autenticación
                .anyRequest().authenticated()
                .and()
                .addFilter(customAthenticationFilter)
                .addFilterBefore(new CustomAthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
