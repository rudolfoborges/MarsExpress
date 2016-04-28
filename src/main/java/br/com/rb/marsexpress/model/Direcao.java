package br.com.rb.marsexpress.model;

public enum Direcao {

	N("N"),
	S("S"),
	E("E"),
	W("W");
	
	private String direcao;
	
	Direcao(String direcao){
		this.direcao = direcao;
	}
	
	public String getValue(){
		return direcao;
	}
	
	
}
