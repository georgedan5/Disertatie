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
                .withUser("ang")
                .password(passwordEncoder().encode("1234"))
                .roles("ANG")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()/*.anyRequest().authenticated()*/.
                antMatchers("/").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/home").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/angajat/list").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/abonament/new").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/angajat/list2").hasRole("ADMIN")
                .antMatchers("/angajat/new").hasRole("ADMIN")
                .antMatchers("/nutritie").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Antrenamente").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Spate").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Piept").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Triceps").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Biceps").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Abdomen").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/Picioare").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/constientizareCalorii").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/mesaj/new").hasAnyRole("USER","ADMIN","ANG")
                .antMatchers("/abonament_efectuat/list").hasAnyRole("ADMIN","ANG")
                .antMatchers("/produse/list").hasAnyRole("ADMIN","ANG","USER")
                .antMatchers("/mesaj/list").hasAnyRole("ADMIN","ANG")
                .antMatchers("/comanda/list").hasAnyRole("ADMIN","ANG")
                .antMatchers("/comenzi/page/**").hasAnyRole("ADMIN","ANG")
                .antMatchers("/comanda/detalii/**").hasAnyRole("ADMIN","ANG")
                .antMatchers("/mesaj/delete/**").hasAnyRole("ADMIN","ANG")
                .antMatchers("/produs/new").hasAnyRole("ADMIN","ANG")
                .antMatchers("/produs/update/**").hasAnyRole("ADMIN","ANG")
                .antMatchers("/produs/delete/**").hasAnyRole("ADMIN","ANG")
                .antMatchers("/abonament_efectuat/delete/**").hasAnyRole("ADMIN","ANG")
                .and().
                formLogin().loginPage("/showLogInForm").loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();


    }

}
