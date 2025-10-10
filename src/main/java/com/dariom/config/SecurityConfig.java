package com.dariom.config;

import com.dariom.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  CsrfCookieFilter csrfCookieFilter() {
    return new CsrfCookieFilter();
  }

  @Bean
  SecurityFilterChain security(HttpSecurity http, CsrfCookieFilter csrfCookieFilter)
      throws Exception {
    var csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse();
    var csrfHandler = new CsrfTokenRequestAttributeHandler();

    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/**").permitAll())
        .csrf(csrf -> csrf
            .csrfTokenRepository(csrfRepo)
            .csrfTokenRequestHandler(csrfHandler))
        .addFilterAfter(csrfCookieFilter, CsrfFilter.class);

    return http.build();
  }
}
