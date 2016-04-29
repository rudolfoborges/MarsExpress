package br.com.rb.marsexpress.model.acao;

import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Posicao;

public class AcaoVirarParaDireita implements Acao {

	@Override
	public Posicao executar(Posicao posicao) {
		Direcao novaDirecao = Direcao.valueOf(posicao.getDirecao().getDireita());
		return new Posicao(posicao.getX(), posicao.getY(), novaDirecao);
	}

}
