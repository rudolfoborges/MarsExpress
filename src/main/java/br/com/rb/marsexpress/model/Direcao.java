package br.com.rb.marsexpress.model;

public enum Direcao {

	N("N", "W", "E"),
	E("E", "N", "S"),
	S("S", "E", "W"),
	W("W", "S", "N");
	
	private String direcao;
	private String esquerda;
	private String direita;
	
	Direcao(String direcao, String esquerda, String direita){
		this.direcao = direcao;
		this.esquerda = esquerda;
		this.direita = direita;
	}
	
	public String getValue(){
		return direcao;
	}
	
	public String getEsquerda(){
		return esquerda;
	}
	
	public String getDireita(){
		return direita;
	}
	
	public boolean comparar(Direcao direcao){
		return this.getValue().equals(direcao.getValue());
	}
	
}
