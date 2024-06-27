package com.flower.star.security;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private static final String [] ACCESS_PUBLIC= {
			"/",
			"/sample/**",
			"/board/**",
			"/starspot/**",
            "/mooncalendar/**",
            "/observatory/**",
            "/uploadimages/**",
			"/assets/**",
            "/vendor/**",
            "/favicon.ico"
	};
	
	private static final String [] ACCESS_GUEST = {
			"/member/login",
			"/member/signup"
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
				.requestMatchers(this::isDispatcherFowardRequest).permitAll()   //dispatcher 허용 방식을 커스텀으로 만듬
				.requestMatchers(this::isDispatcherIncludeRequest).permitAll()
				.antMatchers(ACCESS_PUBLIC).permitAll()
				.antMatchers(ACCESS_GUEST).anonymous()
				.antMatchers(ACCESS_MANAGER).hasAuthority("MANAGER")
				.anyRequest().authenticated()
			.and()
				.csrf().disable()    // csrf token 비활성화 
				.formLogin()    // 로그인 관련
					.loginPage("/member/login")
					.loginProcessingUrl("/member/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.successHandler(customAuthenticationSuccessHandler())
					.failureHandler(new SecurityLoginFailureHandler())   //로그인 실패 시 erorr 쿼리스트링 띄워주는 핸들러
			.and()
				.logout()   // 로그아웃 관련
					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.clearAuthentication(true)
			.and()
				.exceptionHandling()
					.accessDeniedHandler(new SecurityAccessDeniedHandler()); //접근 실패 시 index 페이지로 보내는 핸들러
	}
	
	
	@Bean
	protected AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/");
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		
		return successHandler;
	}
	
	// FORWARD 타입의 디스패처 요청을 허용 하는 메서드
	private boolean isDispatcherFowardRequest(HttpServletRequest request) {	
		return request.getDispatcherType() == DispatcherType.FORWARD;
	}
	
	// INCLUDE 타입의 디스패처 요청을 허용하는 메서드
	private boolean isDispatcherIncludeRequest(HttpServletRequest request) {
		return request.getDispatcherType() ==  DispatcherType.INCLUDE;
	}
	
}
