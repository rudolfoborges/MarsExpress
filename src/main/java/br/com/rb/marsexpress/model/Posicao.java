package br.com.rb.marsexpress.model;

public class Posicao {

	private int x, y;
	private Direcao direcao;
	
	public Posicao(int x, int y, Direcao direcao){
		this.x = x;
		this.y = y;
		this.direcao = direcao;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	public String toString() {
		return String.format("X: %s, Y: %s, Direçao: %s", x, y, direcao.getValue());
	}
}
