package br.com.rb.marsexpress.domain;

public class Planalto {
	private int x, y;
	
	public Planalto(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean verificarPosicao(Posicao posicao){
		if(posicao.getX() < 0 || posicao.getY() < 0){
			return false;
		}
		
		if(posicao.getX() > getX() || posicao.getY() > getY()){
			return false;
		}
		
		return true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
