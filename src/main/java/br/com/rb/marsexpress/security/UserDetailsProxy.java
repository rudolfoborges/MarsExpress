package br.com.rb.marsexpress.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.rb.marsexpress.model.Funcionario;

public class UserDetailsProxy implements UserDetails {
	
	private static final long serialVersionUID = 1719821006812886542L;
	
	private final Funcionario funcionario;

	public UserDetailsProxy(Funcionario funcionario){
		this.funcionario = funcionario;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	public String getPassword() {
		return funcionario.getSenha();
	}

	public String getUsername() {
		return funcionario.getNome();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
}
