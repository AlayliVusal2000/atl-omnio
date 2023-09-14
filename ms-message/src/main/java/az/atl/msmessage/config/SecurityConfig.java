package az.atl.msmessage.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**")
                .permitAll()
                .requestMatchers("/swagger-ui/index.html/**")
                .permitAll()
                .requestMatchers("/api-docs/**")
                .permitAll()
//                .requestMatchers("/profile/**")
//                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/send")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/send/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889/messages")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889/messages/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889/messages/send")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8889/messages/send/")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/send")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/messages/send/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089/messages")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089/messages/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089/messages/send")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"http://localhost:8089/messages/send/")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"/product/allProducts").hasAnyAuthority("ADMIN","USER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
