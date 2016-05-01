package br.com.rb.marsexpress.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	public CustomUserDetailsService(){
		
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
