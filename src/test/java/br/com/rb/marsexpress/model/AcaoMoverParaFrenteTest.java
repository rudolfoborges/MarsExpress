package br.com.rb.marsexpress.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.domain.Acao;
import br.com.rb.marsexpress.domain.AcaoMoverParaFrente;
import br.com.rb.marsexpress.domain.Direcao;
import br.com.rb.marsexpress.domain.Posicao;

public class AcaoMoverParaFrenteTest {

	private Acao acao = null;
	
	@Before
	public void before(){
		acao = new AcaoMoverParaFrente();
	}
	
	
	@Test
	public void testMoverParaFrenteNorte(){
		Posicao posicao = new Posicao(1, 1, Direcao.N);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertEquals(novaPosicao.getX(), 1);
		Assert.assertEquals(novaPosicao.getY(), 2);
	}
	
	@Test
	public void testMoverParaFrenteSul(){
		Posicao posicao = new Posicao(1, 1, Direcao.S);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertEquals(novaPosicao.getX(), 1);
		Assert.assertEquals(novaPosicao.getY(), 0);
	}
	
	@Test
	public void testMoverParaFrenteLeste(){
		Posicao posicao = new Posicao(1, 1, Direcao.E);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertEquals(novaPosicao.getX(), 2);
		Assert.assertEquals(novaPosicao.getY(), 1);
	}
	
	@Test
	public void testMoverParaFrenteOeste(){
		Posicao posicao = new Posicao(1, 1, Direcao.W);
		Posicao novaPosicao = acao.executar(posicao);
		
		Assert.assertEquals(novaPosicao.getX(), 0);
		Assert.assertEquals(novaPosicao.getY(), 1);
	}
}
