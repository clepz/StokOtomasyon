package com.tarikaskin.stok.configurations;

import com.tarikaskin.stok.services.myUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        //auth.inMemoryAuthentication()
         //       .withUser("admin").password(yeniEncoder().encode("admin")).roles("ADMIN","USER").and()
           //     .withUser("user").password(yeniEncoder().encode("user"));
        auth.userDetailsService(userDetailsService()).passwordEncoder(yeniEncoder());
        //auth.inMemoryAuthentication()
          //      .withUser("deneme").password(yeniEncoder().encode("deneme")).roles("ADMIN");
    }

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
        return new myUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.csrf().disable()
                .authorizeRequests()
                .antMatchers("/rol").hasAnyRole("ADMIN","USER","KASIYER")
                .antMatchers("/admin**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/kasiyer**").hasAnyRole("KASIYER","ADMIN")
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder yeniEncoder(){
        return new BCryptPasswordEncoder();
    }

}
