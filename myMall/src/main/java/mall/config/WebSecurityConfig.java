package mall.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public AuthenticationFailureHandler authenticationFailureHandler;
    

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/css/**", "/js/**", "/img/**","/assets/**").permitAll()
			.antMatchers("/guest/**").permitAll()
			.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/loginForm") // default : /login
				.loginProcessingUrl("/j_spring_security_check")
				// .failureUrl("/loginForm?error") // default : /login?error
				// .defaultSuccessUrl("/")
				.failureHandler(authenticationFailureHandler)
				.usernameParameter("my_id") // default : j_username
				.passwordParameter("my_pass") // default : j_password
				.permitAll();

		http.logout()
			.logoutUrl("/logout") // default
			.logoutSuccessUrl("/")
			.permitAll();
		
		http.exceptionHandling().accessDeniedPage("/denine");

		// 개발중에는 꺼 놓는다.
		http.csrf().disable();

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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//			System.out.println(passwordEncoder().encode("1234"));
			
			auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT id AS userName, pw, enabled " + " FROM TBL_USER WHERE id = ?")
				.authoritiesByUsernameQuery("SELECT id AS userName, CASE WHEN USERAUTH = 1 THEN 'ROLE_ADMIN' WHEN USERAUTH = 2 THEN 'ROLE_USER' ELSE 'ROLE GUEST' END AS authority " + " FROM TBL_USER WHERE id = ?")
				.passwordEncoder(new BCryptPasswordEncoder());
			
//			
//			Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
//			System.out.println(auth2.getPrincipal());
			System.out.println("??????????????");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override // 더블슬래시 가능하도록
	public void configure(WebSecurity web) throws Exception {
	    web.httpFirewall(defaultHttpFirewall());
	}
	@Bean // 더블슬래시 가능하도록
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}

}
