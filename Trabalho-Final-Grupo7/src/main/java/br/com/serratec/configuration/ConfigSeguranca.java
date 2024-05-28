package br.com.serratec.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.serratec.security.JwtAuthenticationFilter;
import br.com.serratec.security.JwtAuthorizationFilter;
import br.com.serratec.security.JwtUtil;
import br.com.serratec.service.UsuarioDetailsImpl;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {

	@Autowired
	private JwtUtil jwtUtil;
	 @Autowired
	    private UsuarioDetailsImpl userDetailsImpl;
	
	 @Autowired
		private PasswordEncoder passwordEncoder;

	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth.userDetailsService(userDetailsImpl).passwordEncoder(passwordEncoder);
		}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080/","http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()).disable())
				.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
					requests.requestMatchers(HttpMethod.GET, "/usuarios").permitAll();
					requests.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();
					requests.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
					requests.requestMatchers(HttpMethod.GET, "/funcionarios").hasRole("ADMIN").anyRequest()
							.authenticated();
				}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers((headers) -> headers.disable());
		
		http.addFilter(new JwtAuthenticationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil,
				userDetailsImpl));
		return http.build();}
	
	

		    
		
	    
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


	  @Bean
	    BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	
//	@Bean
//	InMemoryUserDetailsManager userDetailsManager() {
//		System.out.println("Autenticação");
//		
//		UserDetails user = User.builder()
//			    .username("roni")
//			    .password("{noop}123456")
//			    .roles("ADMIN")
//			    .build();
//
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(user);
//		return userDetailsManager;
//	}
}

