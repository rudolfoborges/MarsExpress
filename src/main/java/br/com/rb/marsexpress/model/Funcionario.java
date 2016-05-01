package br.com.rb.marsexpress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.rb.marsexpress.repository.FuncionarioRepository;

@Entity@Table(name="funcionario")
public class Funcionario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public Funcionario() {}

	public Funcionario(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public void salvar(FuncionarioRepository funcionarioRepository){
		funcionarioRepository.save(this);
	}
	
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(nullable=false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(unique=true, nullable=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Column(nullable=false)
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
