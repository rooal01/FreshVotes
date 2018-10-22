package com.alan.freshvotes.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
@Bean
public PasswordEncoder getPasswordEncoder(){
	return new BCryptPasswordEncoder();
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.passwordEncoder(getPasswordEncoder())
.withUser("alan")
.password(getPasswordEncoder().encode("Password"))
.roles("USER");
}

@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		http.csrf().disable() - This was the original setting. We now enable CSRF protection(Default setting) into all PATCH, POST, PUT, and DELETE – not GET operations so it can be used safely
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().hasRole("USER")
		.and().formLogin().loginPage("/login")
//		Added to redirect to homepage after successful login.
		.permitAll().successForwardUrl("/home")
		
		.and()
		.logout().logoutUrl("/logout").permitAll()
		;
	}

}
