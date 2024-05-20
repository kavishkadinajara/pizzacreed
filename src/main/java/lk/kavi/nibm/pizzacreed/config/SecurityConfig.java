package lk.kavi.nibm.pizzacreed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/pizzacreed/pizza/menu").permitAll() // Allow access to the menu endpoint
                .requestMatchers("/api/pizzacreed/pizza/addPizza").permitAll() // Restrict access to addPizza endpoint
                .anyRequest().permitAll()
            )
            .httpBasic(); // Use Basic authentication (if needed)

        return http.build();
    }
}
