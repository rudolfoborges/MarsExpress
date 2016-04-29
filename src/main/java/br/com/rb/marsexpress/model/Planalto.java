package br.com.rb.marsexpress.model;

public class Planalto {
	private int x, y;
	
	public Planalto(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public static Planalto build(String instrucao){
		String[] xy = instrucao.split(" ");
		int x = Integer.parseInt(xy[0].trim());
		int y = Integer.parseInt(xy[1].trim());
		return new Planalto(x, y);
	}
	
	public boolean verificarPosicao(Posicao posicao){
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
