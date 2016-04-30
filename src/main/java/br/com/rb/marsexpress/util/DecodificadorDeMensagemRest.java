package br.com.rb.marsexpress.util;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;

@Component
@Qualifier("rest")
public class DecodificadorDeMensagemRest implements DecodificadorDeMensagem {

	@Override
	public Planalto decodificarPlanalto(List<String> instrucoes) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

	@Override
	public Posicao decodificarPosicao(String mensagem) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

	@Override
	public List<Comando> decodificarComandos(String mensagem) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

	@Override
	public List<String> decodificarInstrucoesGerais(InputStream in) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

}
