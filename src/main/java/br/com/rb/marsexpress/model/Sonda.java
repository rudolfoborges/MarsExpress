package br.com.rb.marsexpress.model;

import br.com.rb.marsexpress.exception.HoustonIHaveAProblemException;

public class Sonda {

	private int lancamento;
	private String nome;
	private Planalto planalto;
	private Posicao posicao;
	
	public Sonda(int lancamento, String nome){
		this.lancamento = lancamento;
		this.nome = nome;
	}
	
	public void aterrissar(Planalto planalto, Posicao posicao){
		this.planalto = planalto;
		this.posicao = posicao;
		
		if(!planalto.verificarPosicao(posicao)){
			throw new HoustonIHaveAProblemException(String.format("A sonda %s aterrissou fora do planalto. A aterrissagem falhou :(", nome));
		}
	}
	
	public void receberComando(final Comando comando){
		posicao = comando.getAcao().executar(posicao);
		
		if(!planalto.verificarPosicao(posicao)){
			throw new HoustonIHaveAProblemException(String.format("Socorro... Eu sou a sonda %s e estou perdida. Por favor, mande uma nova sonda!", nome));
		}
	}
	
	public Posicao informarPosicao(){
		return posicao;
	}
	
	public int getLancamento(){
		return lancamento;
	}
	
	public String getNome(){
		return nome;
	}
	
}


