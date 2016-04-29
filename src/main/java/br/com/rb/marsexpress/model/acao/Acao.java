package br.com.rb.marsexpress.model.acao;

import br.com.rb.marsexpress.model.Posicao;

public interface Acao {

	Posicao executar(Posicao posicao);
	
}
