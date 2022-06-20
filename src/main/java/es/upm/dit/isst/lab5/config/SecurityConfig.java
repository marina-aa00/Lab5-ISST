package es.upm.dit.isst.lab5.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource ds;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds)
        .usersByUsernameQuery("select username, password, enabled from users where username=?")
        .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //Define who can access the resources
        .antMatchers("/").permitAll()
        .antMatchers("/profesores").hasAnyRole("PROF")
        .antMatchers("/alumnos").hasAnyRole("ALUM")
        .antMatchers("/todos").hasAnyRole("ALUM", "PROF")
        .anyRequest().authenticated().and()
        .formLogin().permitAll().and()
        .logout().permitAll().and()
        .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers("h2/*");
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

   
}
