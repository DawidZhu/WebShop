package com.example.myapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * keyCloak
 * Keycloak 是一个开源的身份和访问管理解决方案，它使得用户认证和授权变得更加简单。
 * Keycloak 可以轻松地集成到现有的应用程序中，为应用程序提供安全的用户认证和授权机制，同时支持多种标准协议，如OAuth 2.0、OpenID Connect、SAML 等
 *
 *
 *
 *
 *
 */

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

		serverHttpSecurity
			.csrf(ServerHttpSecurity.CsrfSpec::disable)
			.authorizeExchange(exchange ->
				exchange.pathMatchers("/eureka/**")
					.permitAll()
					.anyExchange()
					.authenticated())
			.oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));

		return serverHttpSecurity.build();
	}

}
