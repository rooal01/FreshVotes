package com.alan.freshvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.freshvotes.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
//Note in above we have provided the domain object User and its ID type 
	
//	This works becuase we have a Username field in the object user. Important to use findByProperty convention...
	User findByUsername(String username);

}
