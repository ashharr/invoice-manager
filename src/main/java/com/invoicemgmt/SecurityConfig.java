package com.invoicemgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.invoicemgmt.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/invoice").authenticated()
				.antMatchers(HttpMethod.DELETE, "/invoice/**").hasAnyAuthority("ADMIN")
				.antMatchers("/beneficiary/**").permitAll()			// hasAnyAuthority("ADMIN") if we select this only admin 
				.antMatchers("/edit-invoice/**").hasAnyAuthority("ADMIN")		// i.e. harry will be able to access customer API
				.antMatchers("/add-invoice/**").authenticated()
				.antMatchers("/login").permitAll()
				.antMatchers("/sign-up").permitAll()
				.and()
				.httpBasic();
		http.cors();
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticator());
	}

	private DaoAuthenticationProvider getAuthenticator() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(myUserDetailsService);
		dao.setPasswordEncoder(getPasswordEncoder());
		return dao;
	}

	@Primary
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}