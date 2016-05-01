package br.com.rb.marsexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rb.marsexpress.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByEmail(String email);	
	
}
