//package com.user.userservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final PasswordEncoderConfig passwordEncoder;
//
//    public SecurityConfig(PasswordEncoderConfig passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    //    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .cors(Customizer.withDefaults())  // Enable CORS
////                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF
////                .authorizeHttpRequests(request -> request
////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // Allow all preflight (OPTIONS) requests
////                        .requestMatchers(HttpMethod.POST, "/user/create", "/cart/add").permitAll()
////                        .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
////                .logout(LogoutConfigurer::permitAll);
////        return http.build();
////    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomUserDetailsService();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/user/create", "/user/token", "/user/validate","/user","/cart/**","/user/**").permitAll())
//                .authenticationProvider(authenticationProvider());
//        return http.build();
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
//        return authenticationProvider;
//    }
//
//}
