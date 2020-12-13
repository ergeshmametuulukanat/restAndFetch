package com.company.config;

import com.company.config.handler.LoginSuccessHandler;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler successUserHandler;

    public SecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,
                          LoginSuccessHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                // разрешаем входить на /user пользователям с ролью User
                .antMatchers("/user").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .anyRequest().authenticated()
                // Spring сам подставит свою логин форму
                .and()
                .formLogin()
                //.loginPage("/login")
                //.failureUrl("/login-error")
                //.permitAll()
                /*.usernameParameter("email")
             .passwordParameter("password")*/
                .successHandler(successUserHandler);

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    private final UserDetailsService userDetailsService;
//    private final LoginSuccessHandler successUserHandler;
//
//    public SecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,
//                          LoginSuccessHandler successUserHandler) {
//        this.userDetailsService = userDetailsService;
//        this.successUserHandler = successUserHandler;
//    }
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
////                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable();
//
//        http.formLogin()
//                .loginPage("/login")
//                .successHandler(successUserHandler)
//                .loginProcessingUrl("/login")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .permitAll();
//
//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .and().csrf().disable();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/login").anonymous()
//                .antMatchers("/**").anonymous()
//                .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
//                .antMatchers("/user").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//                .anyRequest().authenticated();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}
