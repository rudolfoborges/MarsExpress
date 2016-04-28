package br.com.rb.marsexpress.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.HoustonIHaveAProblemException;

public class SondaTest {

	private static final String SONDA_NOME = "Sonda Protótipo";
	
	private Planalto planalto;
	
	@Before
	public void before(){
		planalto = new Planalto(5, 5);
	}
	
	@Test
	public void testAterrisar(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(1, 2, Direcao.N));
	}
	
	@Test(expected=HoustonIHaveAProblemException.class)
	public void testFalhaNaAterrissagemX(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(6, 1, Direcao.N));
	}
	
	@Test(expected=HoustonIHaveAProblemException.class)
	public void testFalhaNaAterrissagemY(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(1, 6, Direcao.N));
	}
	
	@Test(expected=HoustonIHaveAProblemException.class)
	public void testFalhaNaAterrissagemXY(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(6, 6, Direcao.N));
	}
	
	@Test
	public void testToString(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		String esperado = String.format("Eu sou a sonda %s. O planeta vermelho é meu provável destino.", SONDA_NOME);
		Assert.assertTrue(sonda.toString().equals(esperado));
	}
}
