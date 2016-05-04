package br.com.rb.marsexpress.oauth;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.rb.marsexpress.domain.Funcionario;

public class UserDetailsBuilder {
	
	public static UserDetails build(Funcionario funcionario){
		return new UserDetailsProxy(funcionario);
	}
	
}
