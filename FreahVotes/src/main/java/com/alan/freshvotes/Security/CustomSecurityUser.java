package com.alan.freshvotes.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alan.freshvotes.domain.User;

public class CustomSecurityUser extends User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331959268213975175L;
	
	
	public CustomSecurityUser() {}
	public CustomSecurityUser(User user) {
		
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getPassword());
	}

	@Override
	public Set<Authority> getAuthorities() {

		return this.getAuthorities();
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getPassword();
	}

	@Override
	public String getUsername() {
		// becuase we have extended the user class (Parent Class) we can now use this.getUsername/password and so on from the user class
		return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
