package br.com.rb.marsexpress.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.model.Sonda;

public class NasaServiceTest {

	private NasaService nasaService;
	
	@Before
	public void before(){
		nasaService = new NasaService();
	}
	
	@Test
	public void testCriarNovasSondas(){
		for(int i = 1; i < 7; i++){
			Sonda sonda = nasaService.criarNovaSonda();
			Assert.assertTrue(i == sonda.getNumeroDeSerie());
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriacaoDeSondasEmExcesso() {
		for(int i = 1; i < 8; i++){
			Sonda sonda = nasaService.criarNovaSonda();
		}
	}
	
}
