package br.com.rb.marsexpress.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.domain.Direcao;
import br.com.rb.marsexpress.domain.Planalto;
import br.com.rb.marsexpress.domain.Posicao;

public class PlanaltoTest {

	private Planalto planalto;
	
	@Before
	public void before(){
		planalto = new Planalto(5, 5);
	}
	
	@Test
	public void testPosicaoNoPlanaltoXY(){
		Posicao posicao = new Posicao(4, 4, Direcao.N);
		Assert.assertTrue(planalto.verificarPosicao(posicao));
	}
	
	@Test
	public void testPosicaoForaDoPlanaltoXY(){
		Posicao posicao = new Posicao(6, 6, Direcao.N);
		Assert.assertFalse(planalto.verificarPosicao(posicao));
	}
	
	
	@Test
	public void testPosicaoForaDoPlanaltoX(){
		Posicao posicao = new Posicao(6, 1, Direcao.N);
		Assert.assertFalse(planalto.verificarPosicao(posicao));
	}
	
	@Test
	public void testPosicaoForaDoPlanaltoY(){
		Posicao posicao = new Posicao(1, 6, Direcao.N);
		Assert.assertFalse(planalto.verificarPosicao(posicao));
	}
	
}
