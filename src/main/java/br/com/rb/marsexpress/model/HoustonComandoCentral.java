package br.com.rb.marsexpress.model;

/**
 * I am Houston. :)
 * @author rudolfoborges
 *
 */
public class HoustonComandoCentral {

	private static final String[] SONDAS = {"Spirit", "Opportunity", "Curiosity", "Mars Pathfinder", "Viking", "ExoMars"};
	private int sondasEnviadas = 0;
	
	public void enviarSonda(){
		if(sondasEnviadas > (SONDAS.length - 1)){
			throw new IllegalArgumentException("A verba do projeto Mars Express chegou ao fim. Lamentamos não ter encontrado nada até agora!");
		}
		
		String nome = SONDAS[sondasEnviadas];
		Sonda sonda = new Sonda(++sondasEnviadas, nome);
	}
	
}
