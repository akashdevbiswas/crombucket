package com.cromxt.auth.config;

import com.cromxt.authentication.JwtService;
import com.cromxt.auth.entity.Role;
import com.cromxt.auth.entity.UserEntity;
import com.cromxt.authentication.JwtServiceImpl;
import com.cromxt.http.ResponseBuilder;
import com.cromxt.http.ResponseBuilderImpl;
import com.cromxt.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.hc.client5.http.config.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return username -> {
            Mono<UserEntity> userEntityMono = userRepository.findByUsernameOrEmail(username, username);
            return userEntityMono.map(userEntity -> (UserDetails) userEntity).switchIfEmpty(
                    Mono.error(new UsernameNotFoundException("No user found with email or Username " + username)));
        };
    }

    @Bean
    public JwtService jwtService(Environment environment) {
        String secret = environment.getProperty("JWT_SECRET", String.class);
        Long expiration = environment.getProperty("JWT_EXPIRATION", Long.class);
        assert secret != null && expiration != null;
        return new JwtServiceImpl(secret, expiration);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
            ReactiveUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(
                userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder);
        return authManager;
    }

    @Bean
    ResponseBuilder responseBuilder() {
        return new ResponseBuilderImpl();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ConfigurableApplicationContext context) {
        return args -> {
            userRepository.existsByUsername("admin")
                    .flatMap(exists -> {
                        if (exists) {
                            log.info("Admin user already exists");
                            return Mono.empty();
                        }
                        UserEntity admin = UserEntity.builder()
                                .username("admin")
                                .firstName("Admin")
                                .lastName("Admin")
                                .email("admin@mail.com")
                                .password(passwordEncoder().encode("password"))
                                .role(Role.ADMIN)
                                .build();
                        log.info("Creating admin user");
                        return userRepository.save(admin);
                    })
                    .doOnNext((ignored)->log.info("Admin created successfully"))
                    .doOnError(err -> {
                        log.error("Error creating admin user", err);
                        context.close();
                    })
                    .subscribe();
        };
    }
}
