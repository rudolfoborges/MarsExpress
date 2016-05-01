package br.com.rb.marsexpress.security;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.rb.marsexpress.model.Funcionario;

public class UserDetailsBuilder {
	
	public static UserDetails build(Funcionario funcionario){
		return new UserDetailsProxy(funcionario);
	}
	
}
