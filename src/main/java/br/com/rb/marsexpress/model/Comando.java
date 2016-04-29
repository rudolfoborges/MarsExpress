package br.com.rb.marsexpress.model;

import br.com.rb.marsexpress.model.acao.Acao;
import br.com.rb.marsexpress.model.acao.AcaoMoverParaFrente;
import br.com.rb.marsexpress.model.acao.AcaoVirarParaDireita;
import br.com.rb.marsexpress.model.acao.AcaoVirarParaEsquerda;

public enum Comando {

	L("L", new AcaoVirarParaEsquerda()),
	R("R", new AcaoVirarParaDireita()),
	M("M", new AcaoMoverParaFrente());
	
	private String comando;
	private Acao acao;
	
	Comando(String comando, Acao acao){
		this.comando = comando;
		this.acao = acao;
	}
	
	public String getValue(){
		return comando;
	}
	
	public Acao getAcao() {
		return acao;
	}
	
	public static boolean verificarComando(String comando){
		try {
			valueOf(comando);
			return true;
		} catch (IllegalArgumentException ex){
			return false;
		}
	}
}
