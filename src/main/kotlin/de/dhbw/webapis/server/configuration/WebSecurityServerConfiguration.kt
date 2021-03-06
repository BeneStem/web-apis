package de.dhbw.webapis.server.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFluxSecurity
class WebSecurityServerConfiguration {

  @Bean
  fun userDetailsService(): MapReactiveUserDetailsService {
    val admin = User
      .withDefaultPasswordEncoder()
      .username("admin")
      .password("password")
      .roles("ADMIN_ROLE")
      .build()
    return MapReactiveUserDetailsService(admin)
  }

  @Bean
  fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
    return http
      .csrf().disable()
      .authorizeExchange()
      .pathMatchers(HttpMethod.POST, "/**").hasRole("ADMIN_ROLE")
      .anyExchange().permitAll()
      .and()
      .httpBasic()
      .and()
      .build()
  }
}

@Configuration
class CorsConfig : WebFluxConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
      .allowedMethods("*")
  }
}
