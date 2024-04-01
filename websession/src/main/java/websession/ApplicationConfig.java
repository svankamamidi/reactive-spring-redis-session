package websession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import lombok.extern.slf4j.XSlf4j;

@Configuration
@EnableWebFluxSecurity
@EnableWebFlux
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@XSlf4j
public class ApplicationConfig implements WebFluxConfigurer{
	@Bean
	//@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityWebFilterChain configureAPIEndPoints(ServerHttpSecurity http) throws Exception {
		log.debug("load Security Config");

		http.authorizeExchange().pathMatchers("/api/websession")
				.permitAll()
				//.authenticated()			
			.and()
				.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
			//.oauth2ResourceServer().jwt();

		return http.build();
	}
}
