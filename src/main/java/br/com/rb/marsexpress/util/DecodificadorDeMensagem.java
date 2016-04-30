package br.com.rb.marsexpress.util;

import java.io.InputStream;
import java.util.List;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;

public interface DecodificadorDeMensagem {

	Planalto decodificarPlanalto(List<String> instrucoes);
	
	Posicao decodificarPosicao(String mensagem);
	List<Comando> decodificarComandos(Object mensagem);
	
	List<String> decodificarInstrucoesGerais(InputStream in);
}
