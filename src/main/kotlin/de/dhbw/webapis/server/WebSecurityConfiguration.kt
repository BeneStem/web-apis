package de.dhbw.webapis.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User

@Configuration
@EnableWebFluxSecurity
class WebSecurityConfiguration {

    @Bean
    fun userDetailsService() = MapReactiveUserDetailsService(User
            .withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build())

    @Bean
    fun securityWebFilterChain(serverHttpSecurity: ServerHttpSecurity) = serverHttpSecurity
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
            .pathMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
            .pathMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
            .pathMatchers(HttpMethod.GET, "/**").permitAll()
            .and()
            .httpBasic()
            .and()
            .build()
}
