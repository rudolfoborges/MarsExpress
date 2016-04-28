package br.com.rb.marsexpress.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * I am Houston. :)
 * @author rudolfoborges
 *
 */
@Component
public class HoustonComandoCentral {

	private static final String[] SONDAS = {"Spirit", "Opportunity", "Curiosity", "Mars Pathfinder", "Viking", "ExoMars"};
	private int sondasEnviadas = 0;

	private Map<Integer,Sonda> sondas = new HashMap<Integer,Sonda>();

	public void enviarSonda(Planalto planalto, Posicao posicao){

		if(planalto == null || posicao == null){
			return;
		}

		if(sondasEnviadas > (SONDAS.length - 1)){
			throw new IllegalArgumentException("A verba do projeto Mars Express chegou ao fim. Lamentamos não ter encontrado nada até agora!");
		}

		String nome = SONDAS[sondasEnviadas];
		Sonda sonda = new Sonda(++sondasEnviadas, nome);
		sonda.aterrissar(planalto, posicao);
		sondas.put(sondasEnviadas, sonda);
	}

	public void receberInstrucoesGerais(InputStream in, OutputStream out){
		
	}

}
