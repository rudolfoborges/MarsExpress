package br.com.rb.marsexpress.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.rb.marsexpress.domain.Comando;

public class ComandoTest {

	@Test
	public void testVerificarComandos(){
		Assert.assertTrue(Comando.verificarComando("R"));
		Assert.assertTrue(Comando.verificarComando("L"));
		Assert.assertTrue(Comando.verificarComando("M"));
		Assert.assertFalse(Comando.verificarComando("U"));
	}
	
}
