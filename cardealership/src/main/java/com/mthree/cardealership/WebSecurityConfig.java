package com.mthree.cardealership;

import com.mthree.cardealership.security.Encoder;
import com.mthree.cardealership.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Lewi
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Encoder encoder;

    @Autowired
    private UserDetailService userDetailService;

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(encoder.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/addUser").permitAll()
                .antMatchers("/admin/**","/addUser").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/profile", true)
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
//        http.csrf().disable();
//
//        //Pages that don't require you to be logged in
//        http.authorizeRequests().antMatchers("/","/login","/logout","/contact","/details","/home","/homeSpecials",
//                "/new","/specials","/used","/vehicles");
//
//        //Admin only access
//        http.authorizeRequests().antMatchers("/addUser", "/editUser", "/addVehicle",
//                "/editVehicle").access("hasRole('ROLE_ADMIN')");
//
//        http.authorizeRequests().antMatchers("/addVehicle", "/editVehicle")
//                .access("hasRole('ROLE_SALES')");
//
//        //Accessing page with no authorization
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//        //Login Form
//        http.authorizeRequests().and().formLogin()
////                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login")
//                .defaultSuccessUrl("/profile")
//                .failureUrl("/login?error=true")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    }
}
