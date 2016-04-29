package br.com.rb.marsexpress.model.acao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Posicao;
import br.com.rb.marsexpress.model.acao.Acao;
import br.com.rb.marsexpress.model.acao.AcaoVirarParaDireita;

public class AcaoVirarParaDireitaTest {

	private Acao acao = null;
	
	@Before
	public void before(){
		acao = new AcaoVirarParaDireita();
	}
	
	@Test
	public void testApontarParaLeste(){
		Posicao posicao = new Posicao(1, 1, Direcao.N);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertTrue(novaPosicao.getDirecao().comparar(Direcao.E));
	}
	
	@Test
	public void testApontarParaSul(){
		Posicao posicao = new Posicao(1, 1, Direcao.E);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertTrue(novaPosicao.getDirecao().comparar(Direcao.S));
	}
	
	@Test
	public void testApontarParaOeste(){
		Posicao posicao = new Posicao(1, 1, Direcao.S);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertTrue(novaPosicao.getDirecao().comparar(Direcao.W));
	}
	
	@Test
	public void testApontarParaNorte(){
		Posicao posicao = new Posicao(1, 1, Direcao.W);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertTrue(novaPosicao.getDirecao().comparar(Direcao.N));
	}
	
}
