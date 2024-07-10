package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class ProjectSecurityConfig {


//       http.authorizeHttpRequests().anyRequest().authenticated();
//       http.formLogin();
//       http.httpBasic();
//       return http.build();


//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        // Permit All Requests inside the Web Application
//        http.authorizeHttpRequests()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//                .requestMatchers("/home").permitAll()
//
//
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//
//        // Deny All Requests inside the Web Application
//            /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());*/
//
//        return http.build();
//
//    }



//                @Bean
//                SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//                        http.csrf((csrf) -> csrf.disable())
//                                .authorizeHttpRequests((requests) -> requests.requestMatchers("", "/", "/home").permitAll()
//                                        .requestMatchers("/holidays/**").permitAll()
//                                        .requestMatchers("/contact").permitAll()
//                                        .requestMatchers("/saveMsg").permitAll()
//                                        .requestMatchers("/courses").permitAll()
//                                        .requestMatchers("/about").permitAll()
//                                        .requestMatchers("/assets/**").permitAll())
//                                .formLogin(Customizer.withDefaults())
//                                .httpBasic(Customizer.withDefaults());
//                        return http.build();
//                }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
//                        .requestMatchers("", "/", "/home").permitAll()
//                        .requestMatchers("/holidays/**").permitAll()
//                        .requestMatchers("/contact").permitAll()
//                        .requestMatchers("/saveMsg").permitAll()
//                        .requestMatchers("/courses").permitAll()
//                        .requestMatchers("/about").permitAll()
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/error").permitAll()
//                        .requestMatchers("/assets/**").permitAll())
//                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
//                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
//                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
//                        .invalidateHttpSession(true).permitAll())
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg"))
//                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
//                        .requestMatchers("", "/", "/home").permitAll()
//                        .requestMatchers("/holidays/**").permitAll()
//                        .requestMatchers("/contact").permitAll()
//                        .requestMatchers("/saveMsg").permitAll()
//                        .requestMatchers("/courses").permitAll()
//                        .requestMatchers("/about").permitAll()
//                        .requestMatchers("/assets/**").permitAll()
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/logout").permitAll())
//                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
//                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
//                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
//                        .invalidateHttpSession(true).permitAll())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/saveMsg"))
                        .ignoringRequestMatchers(PathRequest.toH2Console()))
                .authorizeHttpRequests((requests) -> requests.requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/holidays/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/courses")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        http.headers(headersConfigurer -> headersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        return http.build();

    }





    @Bean
public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("12345")
            .roles("USER")
            .build();
    UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("54321")
            .roles("USER", "ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user, admin);
}







}
