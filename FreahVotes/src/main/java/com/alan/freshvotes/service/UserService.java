package com.alan.freshvotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alan.freshvotes.Security.Authority;
import com.alan.freshvotes.domain.User;
import com.alan.freshvotes.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	//becuase this is a bean we can autowire it
	
//No idea why this will not autowire. Test when complete	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public User save(User user){
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		user.getAuthorities().add(authority);
		return userRepo.save(user);
	}

}
