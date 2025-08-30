package br.com.ifba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Agora, qualquer requisição começando com /api/ será permitida
                        .requestMatchers("/api/**").permitAll()
                        // Qualquer outra requisição fora de /api/ ainda precisará de autenticação
                        .anyRequest().authenticated()
                );
        // Com .permitAll() para /api/**, o httpBasic pode não ser mais necessário
        // a menos que você tenha outros endpoints fora de /api/ que queira proteger.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}