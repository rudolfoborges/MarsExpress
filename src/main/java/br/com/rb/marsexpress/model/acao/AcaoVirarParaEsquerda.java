package br.com.rb.marsexpress.model.acao;

import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Posicao;

public class AcaoVirarParaEsquerda implements Acao {

	@Override
	public Posicao executar(Posicao posicao) {
		Direcao novaDirecao = Direcao.valueOf(posicao.getDirecao().getEsquerda());
		return new Posicao(posicao.getX(), posicao.getY(), novaDirecao);
	}

}
