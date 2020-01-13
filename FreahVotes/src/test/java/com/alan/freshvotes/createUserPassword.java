package com.alan.freshvotes;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class createUserPassword {
	
	
	@Test
	public void encry(){
		
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String aa= enc.encode("Password1");
		System.out.print(aa);
	}
	
	

}
