package com.napas.qr.qrrealtime.security;

import com.napas.qr.qrrealtime.security.services.UserDetailsServiceImpl;
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

import com.napas.qr.qrrealtime.security.jwt.AuthEntryPointJwt;
import com.napas.qr.qrrealtime.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
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
                .antMatchers("/mngweb/api/auth/**").permitAll()
                .antMatchers("/mngweb/api/role/**").permitAll()
                .antMatchers("/mngweb/api/test/**").permitAll()
                .antMatchers("/mngweb/api/dictionary/**").permitAll()
                .antMatchers("/mngweb/api/reportoffline/**").permitAll()
                .antMatchers("/mngweb/api/portal/captcha").permitAll()
                .antMatchers("/mngweb/api/healthcheck").permitAll()
                .antMatchers("/mngweb/images/napas.png").permitAll()
                .antMatchers("/mngweb/api/PartnerBankAccount/storeNewBankAccount").permitAll()
                .antMatchers(
                        "/mngweb/static/**",
                        "/mngweb/images/**",
                        "/mngweb/*.svg", "/mngweb/*.ico", "/mngweb/*.eot", "/mngweb/*.woff2",
                        "/mngweb/*.ttf", "/mngweb/*.woff", "/mngweb/*.html", "/mngweb/*.js", "/mngweb/*.json",
                        "/mngweb/*.map", "/mngweb/*.bundle.*",
                        "/mngweb/index.html", "/mngweb/", "/mngweb/home/**", "/mngweb/dashboard/**", "/mngweb/admin/**", "/mngweb/login").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
