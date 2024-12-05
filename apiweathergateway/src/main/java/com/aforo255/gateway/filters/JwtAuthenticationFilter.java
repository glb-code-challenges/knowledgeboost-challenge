package com.aforo255.gateway.filters;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Flux;

import javax.crypto.spec.SecretKeySpec;
 
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
	public JwtAuthenticationFilter() {
		super(Config.class);
	}
 
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Value("${jwt.secret}")
	private String secret;
 
	@Override
	public GatewayFilter apply(Config config) {
		// Custom Pre Filter. Suppose we can extract JWT and perform Authentication
		return (exchange, chain) -> {
			logger.info("Start pre filter jwt" + exchange.getRequest());
 
			if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				byte[] bytes = "{\"message\":\"Missing credentials\"}".getBytes(StandardCharsets.UTF_8);
				DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
				return exchange.getResponse().writeWith(Flux.just(buffer));				
			}
 
			String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			logger.info("Show Header ==>> " + authHeader);
			String[] parts;
			try {
				parts = authHeader.split(" ");
				logger.info("Token ==>> " + parts[1]);
				if (parts.length != 2 || !"Bearer".equals(parts[0])) {
					logger.error("Incorrect authorization structure");
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);					
					return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(getMissingCredentialsResponse())));
				}
			} catch (Exception e) {
				logger.error("INVALID_TOKEN EMPTY");
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);				
				return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(getMissingCredentialsResponse())));				
			}
 
			try {
				Key hmacKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
				Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(parts[1]);
			} catch (Exception e) {
				logger.error("INVALID_TOKEN");
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);				
				return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(getMissingCredentialsResponse())));				
			}
			logger.info("End pre filter jwt");
			return chain.filter(exchange);
		};
	}
	
	private byte[] getMissingCredentialsResponse() {
		byte[] bytes = "{\"message\":\"Missing credentials\"}".getBytes(StandardCharsets.UTF_8);		
		return bytes;
	}
 
	public static class Config {
		// Put the configuration properties
	}
}