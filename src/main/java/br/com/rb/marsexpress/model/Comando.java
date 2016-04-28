package br.com.rb.marsexpress.model;

public enum Comando {

	L("L"),
	R("R"),
	M("M");
	
	private String comando;
	
	Comando(String comando){
		this.comando = comando;
	}
	
	public String getValue(){
		return comando;
	}
	
	public boolean verificarComando(String comando){
		try {
			valueOf(comando);
			return true;
		} catch (IllegalArgumentException ex){
			return false;
		}
	}
}
