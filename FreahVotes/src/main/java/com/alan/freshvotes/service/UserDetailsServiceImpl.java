package com.alan.freshvotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alan.freshvotes.Security.CustomSecurityUser;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
//	Note how we implement the UserDetailsService interface and the naming convention used on our interface. This is standard practice

	@Autowired
	private UserRepository userRepo; //Note how the simple addition of this repository provides us will all CRUD operations 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Invalid Username and Password");
		return new CustomSecurityUser(user);
	}

}
