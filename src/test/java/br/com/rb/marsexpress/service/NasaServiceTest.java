package br.com.rb.marsexpress.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;
import br.com.rb.marsexpress.model.Sonda;
import br.com.rb.marsexpress.util.DecodificadorDeMensagemTexto;

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
			Assert.assertEquals(i, sonda.getNumeroDeSerie());
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCriacaoDeSondasEmExcesso() {
		for(int i = 1; i < 8; i++){
			nasaService.criarNovaSonda();
		}
	}
	
	@Test
	public void testLancarSonda(){
		Planalto planalto = new Planalto(5,5);
		Posicao posicao = new Posicao(0,0,Direcao.N);
		Sonda sonda = new Sonda(1, "Sonda Protótipo");
		nasaService.lancarSonda(sonda, planalto, posicao);
	}
	
	@Test
	public void testEnviarComando(){
		Planalto planalto = new Planalto(5,5);
		Posicao posicao = new Posicao(0,0,Direcao.N);
		Sonda sonda = new Sonda(1, "Sonda Protótipo");
		nasaService.lancarSonda(sonda, planalto, posicao);
		nasaService.enviarComando(sonda, Comando.M);
	}
	
	@Test
	public void testEnviarListaDeComandos(){
		Planalto planalto = new Planalto(5,5);
		Posicao posicao = new Posicao(0,0,Direcao.N);
		Sonda sonda = new Sonda(1, "Sonda Protótipo");
		nasaService.lancarSonda(sonda, planalto, posicao);
		nasaService.enviarListaDeComandos(sonda, Arrays.asList(Comando.L, Comando.M, Comando.L, Comando.M, Comando.L, Comando.L));
	}
	
	@Test
	public void testReceberInstrucoesGerais() throws IOException{
		StringBuilder instrucoes = new StringBuilder()
				.append("5 5\n")
				.append("1 2 N\n")
				.append("LMLMLMLMM\n")
				.append("3 3 E\n")
				.append("MMRMMRMRRM\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(instrucoes.toString().getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		nasaService.receberInstrucoesGerais(in, out, new DecodificadorDeMensagemTexto());
		
		StringBuilder relatorioEsperado = new StringBuilder()
				.append("Sonda Spirit - 1 3 N \n") 
				.append("Sonda Opportunity - 5 1 E \n");
		
		Assert.assertEquals(relatorioEsperado.toString(), new String(out.toByteArray()));
		
		out.close();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReceberInstrucoesGeraisComErro() throws IOException{
		StringBuilder instrucoes = new StringBuilder()
				.append("5 5\n")
				.append("1 2 N\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(instrucoes.toString().getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		nasaService.receberInstrucoesGerais(in, out, new DecodificadorDeMensagemTexto());
		
		out.close();
		
	}
	
}
