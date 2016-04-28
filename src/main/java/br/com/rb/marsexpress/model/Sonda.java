package br.com.rb.marsexpress.model;

import br.com.rb.marsexpress.HoustonIHaveAProblemException;

public class Sonda {

	private int numeroDeSerie;
	private String nome;
	private Planalto planalto;
	private Posicao posicao;
	
	public Sonda(int numeroDeSerie, String nome){
		this.numeroDeSerie = numeroDeSerie;
		this.nome = nome;
	}
	
	public void aterrissar(Planalto planalto, Posicao posicao){
		this.planalto = planalto;
		this.posicao = posicao;
		
		if(posicao.getX() > planalto.getX() || posicao.getY() > posicao.getY()){
			throw new HoustonIHaveAProblemException(String.format("A sonda %s aterrissou fora do planalto. A aterrissagem falhou :(", nome));
		}
	}
	
	public void receberComando(Comando comando){
		
	}
	
	public String toString() {
		return String.format("Eu sou a sonda %s. O planeta vermelho é meu provável destino.", nome);
	}
	
}


