package br.com.rb.marsexpress.domain;

public class AcaoVirarParaDireita implements Acao {

	@Override
	public Posicao executar(Posicao posicao) {
		Direcao novaDirecao = Direcao.valueOf(posicao.getDirecao().getDireita());
		return new Posicao(posicao.getX(), posicao.getY(), novaDirecao);
	}

}
