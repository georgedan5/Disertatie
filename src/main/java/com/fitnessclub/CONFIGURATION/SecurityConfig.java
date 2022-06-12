package com.fitnessclub.CONFIGURATION;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("h2")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()/*.anyRequest().authenticated()*/.
                antMatchers("/").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/**").hasRole("ADMIN")

                .antMatchers("/category/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/category/**").hasRole("ADMIN")

                .antMatchers("/customer/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/customer/**").hasRole("ADMIN")

                .antMatchers("/department/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/department/**").hasRole("ADMIN")

                .antMatchers("/employe/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/employe/**").hasRole("ADMIN")

                .antMatchers("/order/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/order/delete/**").hasRole("ADMIN")
                .antMatchers("/order/info/**").hasRole("ADMIN")

                .antMatchers("/provider/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/provider/**").hasRole("ADMIN")

                .antMatchers("/providerinfo/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/providerinfo/**").hasRole("ADMIN").and().

                formLogin().loginPage("/showLogInForm").loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();


    }

}
