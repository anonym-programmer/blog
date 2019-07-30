package pl.robert.blog.app.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import pl.robert.blog.app.user.domain.UserFacade;
import pl.robert.blog.app.user.domain.dto.UserDto;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserFacade facade;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDto user = facade.find();
        auth.inMemoryAuthentication()
                .withUser(user.getUsername())
                .password("{noop}".concat(user.getPassword()))
                .roles(user.getRole());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(NOT_AUTHENTICATED_API).permitAll()
                .antMatchers(AUTHENTICATED_API).authenticated()
                .anyRequest().authenticated()
            .and()
                .cors()
            .and()
                .httpBasic()
            .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    private static final String[] NOT_AUTHENTICATED_API = {
            "/api/login*",
            "/api/user*"
    };

    private static final String[] AUTHENTICATED_API = {
            "/api/admin*"
    };
}
