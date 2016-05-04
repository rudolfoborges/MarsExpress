package br.com.rb.marsexpress.domain;

public class AcaoVirarParaEsquerda implements Acao {

	@Override
	public Posicao executar(Posicao posicao) {
		Direcao novaDirecao = Direcao.valueOf(posicao.getDirecao().getEsquerda());
		return new Posicao(posicao.getX(), posicao.getY(), novaDirecao);
	}

}
