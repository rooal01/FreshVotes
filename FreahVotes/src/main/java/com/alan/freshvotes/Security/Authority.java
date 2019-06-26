package com.alan.freshvotes.Security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.alan.freshvotes.domain.User;

@Entity
public class Authority implements GrantedAuthority{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6041567377061231341L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private String authority;
	private Long id;
	private User user;
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
