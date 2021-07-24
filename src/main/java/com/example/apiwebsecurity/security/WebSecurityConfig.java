package com.example.apiwebsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.apiwebsecurity.security.UserPermission.PRODUCTO_WRITE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/productos/**").hasAuthority(PRODUCTO_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(PRODUCTO_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
               /* .formLogin()   //se le puede dar formato al login de esta forma
                        .failureForwardUrl ("/login?error?prueba")
                        .successForwardUrl("api/productos");*/
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails usuario1 = User.builder()
                .username("jaz")
                .password(passwordEncoder().encode("password"))
                //.roles(UserRole.ADMIN.name())
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();
        UserDetails usuario2 = User.builder()
                .username("juli")
                .password(passwordEncoder().encode("password"))
                .roles(UserRole.CLIENTE.name())
                .build();
        UserDetails usuario3 = User.builder()
                .username("jesi")
                .password(passwordEncoder().encode("password"))
                .roles(UserRole.ADMIN.name())
                .build();

        UserDetails usuario4 = User.builder()
                .username("test")
                .password(passwordEncoder().encode("password"))
                .authorities(UserRole.SELLER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3);
    }

}
