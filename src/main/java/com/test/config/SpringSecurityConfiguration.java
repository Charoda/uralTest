package com.test.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.services.secure.UserService;
import com.test.services.secure.UserServiceImpl;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .and()
//                .csrf().disable()
////                .formLogin().disable()
//                .httpBasic().and()
//                .authorizeHttpRequests()
//                .requestMatchers("/subproject/**").hasRole("ADMIN")
//                .requestMatchers("/project/**").hasRole("ADMIN")
//                .requestMatchers("/getProject").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .build();


        // отключение секьюрности
        return http.csrf().disable().build();

        // отключение секьюрности
//        return http.authorizeHttpRequests()
//                        .requestMatchers("/**")
//        .permitAll()
//                .and().build();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider auProvider = new DaoAuthenticationProvider();
//        auProvider.setPasswordEncoder(passwordEncoder());
//        auProvider.setUserDetailsService(userService);
//        return auProvider;
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
