package br.com.rb.marsexpress.model;

public class Sonda {

	private String nome;
	private Planalto planalto;
	private Posicao posicao;
	
	public Sonda(String nome){
		this.nome = nome;
	}
	
	public void aterrissar(Planalto planalto, Posicao posicao){
		this.planalto = planalto;
		this.posicao = posicao;
	}
	
	public void receberComando(Comando comando){
		
	}
	
	public static void main(String[] args) {
		
		Planalto planalto = new Planalto();
		Posicao posicao = new Posicao(1, 2, Direcao.valueOf("N"));
		System.out.println(posicao.toString());
		
		
		Sonda spirit = new Sonda("Spirit");
		spirit.aterrissar(planalto, posicao);
	}
	
}


