package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserLogoutTrackerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;
    private UserLogoutTrackerService userLogoutTrackerService;
    public SecurityConfig(AuthenticationService authenticationService, UserLogoutTrackerService userLogoutTrackerService) {
        this.authenticationService = authenticationService;
        this.userLogoutTrackerService = userLogoutTrackerService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.logout().logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler(){
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                userLogoutTrackerService.setUserLoggedOut(true);
                super.onLogoutSuccess(request, response, authentication);
            }
        });
    }
}
