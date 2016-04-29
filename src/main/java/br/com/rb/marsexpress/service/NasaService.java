package br.com.rb.marsexpress.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;
import br.com.rb.marsexpress.model.Sonda;

@Service
public class NasaService {

	private static final String[] SONDAS = {"Spirit", "Opportunity", "Curiosity", "Mars Pathfinder", "Viking", "ExoMars"};
	private int sondasEnviadas = 0;

	private Map<Integer,Sonda> sondas = new HashMap<Integer, Sonda>();

	public Sonda criarNovaSonda(){
		if(sondasEnviadas > (SONDAS.length - 1)){
			throw new IllegalArgumentException("A verba do projeto Mars Express chegou ao fim. Lamentamos não ter encontrado nada até agora!");
		}

		String nome = SONDAS[sondasEnviadas];
		Sonda sonda = new Sonda(++sondasEnviadas, nome);
		sondas.put(sondasEnviadas, sonda);
		
		return sonda;
	}
	
	public List<Comando> transformarEmComandos(char[] instrucoes){
		List<Comando> comandos = new ArrayList<Comando>();
		for (char c : instrucoes) {
			comandos.add(Comando.valueOf(String.valueOf(c)));
		}
		return comandos;
	}
	
	public void enviarListaDeComandos(Sonda sonda, List<Comando> comandos){
		for (Comando comando : comandos) {
			enviarComando(sonda, comando);
		}
	}
	
	public void enviarComando(Sonda sonda, Comando comando){
		sonda.receberComando(comando);
	}
	
	public void lancarSonda(Sonda sonda, Planalto planalto, Posicao posicao){
		sonda.aterrissar(planalto, posicao);
	}

	public void receberInstrucoesGerais(InputStream in, OutputStream out){
		List<String> instrucoes = separarInstrucoes(in);
		
		if(instrucoes.size() < 3){
			throw new IllegalArgumentException("As Instruções Gerais não foram devidamente formatadas");
		}
		
		Planalto planalto = Planalto.build(instrucoes.get(0).trim());
		
		Posicao posicao = null;
		List<Comando> comandos = new ArrayList<Comando>();
		
		for(int i = 1; i < instrucoes.size(); i++){
			if(i % 2 != 0){
				posicao = Posicao.build(instrucoes.get(i).trim());
			} else {
				comandos.clear();
				char itens[] = instrucoes.get(i).trim().toCharArray();
				comandos = transformarEmComandos(itens);
				
				Sonda sonda = criarNovaSonda();
				lancarSonda(sonda, planalto, posicao);
				enviarListaDeComandos(sonda, comandos);
				relatorioDePosicao(sonda, out);
			}
			
		}
	}
	
	private List<String> separarInstrucoes(InputStream in){
		List<String> instrucoes = new ArrayList<String>();
		Scanner scanner = new Scanner(in);
		while(scanner.hasNext()){
			instrucoes.add(scanner.nextLine());
		}
		scanner.close();
		return instrucoes;
	}
	
	private void relatorioDePosicao(Sonda sonda, OutputStream out){
		try {
			out.write(String.format("Sonda %s - %s \n",sonda.getNome(), sonda.informarPosicao().toString()).getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
}
