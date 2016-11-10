package pl.edu.agh.weplay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.agh.weplay.security.AjaxAuthenticationFailureHandler;
import pl.edu.agh.weplay.security.AjaxAuthenticationSuccessHandler;
import pl.edu.agh.weplay.security.AjaxLogoutSuccessHandler;

import javax.inject.Inject;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Inject
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Inject
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Inject
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and().csrf().disable()
//            .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/login.html", "/", "/user").permitAll()
                .anyRequest().permitAll()
//            .and()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//            .and()
//                .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
//                .exceptionHandling()
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .formLogin()
                .loginProcessingUrl("/api/authentication") //?
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll();
//            .and()
//                .authorizeRequests()
//                .antMatchers("/index.html", "/home.html", "/login.html", "/", "/user").permitAll()
//                .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
