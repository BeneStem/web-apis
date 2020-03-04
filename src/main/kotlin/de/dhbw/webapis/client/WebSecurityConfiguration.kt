package de.dhbw.webapis.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity

@Configuration
@EnableWebFluxSecurity
class WebSecurityConfiguration {

    @Bean
    fun securityWebFilterChain(serverHttpSecurity: ServerHttpSecurity) = serverHttpSecurity
            .build()
}
