package mall.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import lombok.RequiredArgsConstructor;
import mall.exception.JwtAccessDeniedHandler;
import mall.exception.JwtAuthenticationEntryPoint;
import mall.security.JWTConfigurer;
import mall.security.Role;
import mall.service.CustomUserDetailsService;
import mall.service.JwtAuthTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthTokenProvider jwtAuthTokenProvider;
	private final JwtAuthenticationEntryPoint authenticationErrorHandler;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final CustomUserDetailsService customUserDetailsService;

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(jwtAuthTokenProvider);
	}

	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT를 사용하려면 기본 세션 설정을 꺼야 함
		http.cors(); // 교차출처 리소스 공유(CORS) 설정

		http.exceptionHandling()
			.authenticationEntryPoint(authenticationErrorHandler)
			.accessDeniedHandler(jwtAccessDeniedHandler);

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/css/**", "/js/**", "/img/**", "/assets/**").permitAll()
			.antMatchers("/guest/**").permitAll()
			.antMatchers("/api/v1/login/**").permitAll()
			.antMatchers("/member/**").permitAll()
//			.antMatchers("/member/**").hasAnyAuthority(Role.USER.getCode(), Role.ADMIN.getCode())
//			.antMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.getCode())
//			.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated().and().apply(securityConfigurerAdapter());

		http.formLogin().loginPage("/loginForm") // default : /login
				.loginProcessingUrl("/j_spring_security_check")
				// .failureUrl("/loginForm?error") // default : /login?error
				// .defaultSuccessUrl("/")
				.failureHandler(authenticationFailureHandler)
				.usernameParameter("my_id") // default : j_username
				.passwordParameter("my_pass") // default : j_password
				.permitAll();

		http.logout().logoutUrl("/logout") // default
				.logoutSuccessUrl("/").permitAll();

//		http.exceptionHandling().accessDeniedPage("/denine");

		// 개발중에는 꺼 놓는다.
//		http.csrf().disable();

	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.inMemoryAuthentication()
	 * .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
	 * .and()
	 * .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	 * }
	 */

	@Autowired
	private DataSource dataSource;

	
//	  @Override protected void configure(AuthenticationManagerBuilder auth) throws
//	  Exception{
//		  
////		  
////		  System.out.println("protected void configur");
////		  // System.out.println(passwordEncoder().encode("1234"));
////	  
////	  
////	  
////	  auth.jdbcAuthentication() .dataSource(dataSource)
////	  .usersByUsernameQuery("SELECT id AS userName, pw, enabled " +
////	  " FROM TBL_USER WHERE id = ?")
////	  .authoritiesByUsernameQuery("SELECT id AS userName, CASE WHEN USERAUTH = 1 THEN 'ROLE_ADMIN' WHEN USERAUTH = 2 THEN 'ROLE_USER' ELSE 'ROLE GUEST' END AS authority "
////	  + " FROM TBL_USER WHERE id = ?") .passwordEncoder(new BCryptPasswordEncoder());
//	  
//	  
//	  
////	  auth.userDetailsService(customUserDetailsService).passwordEncoder(
////	  passwordEncoder());
//	  
//	  // // Authentication auth2 =
////	  SecurityContextHolder.getContext().getAuthentication(); //
////	  System.out.println(auth2.getPrincipal());
////	  System.out.println("??????????????"); 
//	  }
	 

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override // 더블슬래시 가능하도록
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")

				// allow anonymous resource requests
				.antMatchers("/");
		web.httpFirewall(defaultHttpFirewall());
	}

	@Bean // 더블슬래시 가능하도록
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

}
