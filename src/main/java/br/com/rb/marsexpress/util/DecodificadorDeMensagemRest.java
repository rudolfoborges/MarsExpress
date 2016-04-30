package br.com.rb.marsexpress.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;

@Component
@Qualifier("rest")
public class DecodificadorDeMensagemRest implements DecodificadorDeMensagem {

	public Planalto decodificarPlanalto(List<String> instrucoes) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

	public Posicao decodificarPosicao(String mensagem) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

	@SuppressWarnings("unchecked")
	public List<Comando> decodificarComandos(Object mensagem) {
		List<Comando> comandos = new ArrayList<Comando>();
		List<String> itens = (List<String>) mensagem;
		for (String comando : itens) {
			comandos.add(Comando.valueOf(comando));
		}
		return comandos;
	}

	public List<String> decodificarInstrucoesGerais(InputStream in) {
		throw new UnsupportedOperationException("O método não é suportado");
	}

}
