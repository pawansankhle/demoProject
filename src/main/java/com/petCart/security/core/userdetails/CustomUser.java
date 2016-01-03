package com.petCart.security.core.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
 * 
 * @author pawan.sankhle
 *
 */
@SuppressWarnings("serial")
public class CustomUser extends User {
   
	
	
	public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
		
	}

	
     
    
    
 
}