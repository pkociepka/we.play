package pl.edu.agh.weplay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import pl.edu.agh.weplay.security.*;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private Properties properties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Inject
    private RememberMeServices rememberMeServices;

    @Inject
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/*", "/js/**", "/libs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
//        .and()
//            .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .rememberMe()
            .rememberMeServices(rememberMeServices)
            .rememberMeParameter("remember-me")
            .key(properties.getSecurity().getRememberMe().getKey())
        .and()
            .formLogin()
            .loginProcessingUrl("/api/authentication")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID", "CSRF-TOKEN")
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/users/**").hasAuthority(AuthoritiesConstants.USER)
            .antMatchers("/new").hasAuthority(AuthoritiesConstants.USER)
            .antMatchers("/player").hasAuthority(AuthoritiesConstants.USER)
            .antMatchers("/api/**").authenticated()
            .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
