package com.napas.qr.security;

import com.napas.qr.security.jwt.AuthEntryPointJwt;
import com.napas.qr.security.jwt.AuthTokenFilter;
import com.napas.qr.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/role/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/api/dictionary/**").permitAll()
                .antMatchers("/api/reportoffline/**").permitAll()
                .antMatchers("/api/healthcheck").permitAll()
                .antMatchers("api/auth/recoverPassword").permitAll()
                .antMatchers("/api/user/no-auth/savePassword").permitAll()
                .antMatchers("/api/portal/captcha").permitAll()
                .antMatchers(
                        "/static/**",
                        "/images/**",
                        "/assets/**",
                        "/*.svg", "/*.ico", "/*.eot", "/*.woff2",
                        "/*.ttf", "/*.woff", "/*.html", "/*.js", "/*.json", "/*.js.map","/app/**","/icons/**",
                        "/*.map", "/*.bundle.*", "/*.css.*", "/*.css",
                        "/index.html", "/", "/home/**", "/dashboard/**", "/admin/**", "/login", "**/healthcheck","**/captcha").permitAll()
               .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
