package ru.maikova.spring.OnlineBank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.maikova.spring.OnlineBank.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class SecurityConfiguration {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfiguration(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/auth/login", "/", "/main/**", "/error", "/resources/static/**").permitAll()
                .anyRequest().hasAnyRole("USER", "MANAGER")
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/process_login")
                .defaultSuccessUrl("/auth/loginSuccess", true)
                //.defaultSuccessUrl("/user/main", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/main/home");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/images/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        //TODO: password with encoder
    }
}
