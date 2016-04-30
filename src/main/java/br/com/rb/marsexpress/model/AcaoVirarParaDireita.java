package br.com.rb.marsexpress.model;

public class AcaoVirarParaDireita implements Acao {

	@Override
	public Posicao executar(Posicao posicao) {
		Direcao novaDirecao = Direcao.valueOf(posicao.getDirecao().getDireita());
		return new Posicao(posicao.getX(), posicao.getY(), novaDirecao);
	}

}
