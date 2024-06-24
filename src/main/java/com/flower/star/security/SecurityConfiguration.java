package com.flower.star.security;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private static final String [] ACCESS_PUBLIC= {
			
			"/",
			"/board/**",
			"/star/**",
	};
	
	private static final String [] ACCESS_GUEST = {
			"/member/login",
			"/member/sigup",
			"/member/myProfile",
			"/member/updateProfile"
	};
	
	private static final String [] ACCESS_MANAGER = {
		// 추후 관리자 페이지 만들면 사용할 수 있음
	};
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
		
	}
	
	
	
	
	@Bean
	protected AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/");
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		
		return successHandler;
	}
	
}
