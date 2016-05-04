package br.com.rb.marsexpress.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rb.marsexpress.domain.Funcionario;
import br.com.rb.marsexpress.repository.FuncionarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final FuncionarioRepository funcionarioRepository;

	@Autowired
	public CustomUserDetailsService(FuncionarioRepository funcionarioRepository){
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByEmail(username);
		
		if(funcionario == null){
			return null;
		}
		
		return UserDetailsBuilder.build(funcionario);
	}

}
