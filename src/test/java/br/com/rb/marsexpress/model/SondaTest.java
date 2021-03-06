package br.com.rb.marsexpress.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.domain.Comando;
import br.com.rb.marsexpress.domain.Direcao;
import br.com.rb.marsexpress.domain.Planalto;
import br.com.rb.marsexpress.domain.Posicao;
import br.com.rb.marsexpress.domain.Sonda;
import br.com.rb.marsexpress.exception.HoustonIHaveAProblemException;

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
	
	
	@Test(expected=HoustonIHaveAProblemException.class)
	public void testMovimentarForaDoPlanalto(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(1, 2, Direcao.N));
		List<String> comandos = Arrays.asList("M", "M", "M", "M", "M", "M", "M", "M", "M");
		
		comandos.forEach(c -> sonda.receberComando(Comando.valueOf(c)));
	} 
	
	@Test
	public void testDesafioSonda1(){
		Sonda sonda = new Sonda(1, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(1, 2, Direcao.N));
		List<String> comandos = Arrays.asList("L", "M", "L", "M", "L", "M", "L", "M", "M");
		
		comandos.forEach(c -> sonda.receberComando(Comando.valueOf(c)));
		
		Assert.assertEquals(sonda.informarPosicao().getX(), 1);
		Assert.assertEquals(sonda.informarPosicao().getY(), 3);
		Assert.assertEquals(sonda.informarPosicao().getDirecao().getValue(), "N");
	}
	
	@Test
	public void testDesafioSonda2(){
		Sonda sonda = new Sonda(2, SONDA_NOME);
		sonda.aterrissar(planalto, new Posicao(3, 3, Direcao.E));
		List<String> comandos = Arrays.asList("M", "M", "R", "M", "M", "R", "M", "R", "R", "M");
		
		comandos.forEach(c -> sonda.receberComando(Comando.valueOf(c)));
		
		Assert.assertEquals(sonda.informarPosicao().getX(), 5);
		Assert.assertEquals(sonda.informarPosicao().getY(), 1);
		Assert.assertEquals(sonda.informarPosicao().getDirecao().getValue(), "E");
	}
}
