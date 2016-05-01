package br.com.rb.marsexpress.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;
import br.com.rb.marsexpress.model.Sonda;
import br.com.rb.marsexpress.util.DecodificadorDeMensagem;

@Component
public class NasaService {

	private static final String[] SONDAS = {"Spirit", "Opportunity", "Curiosity", "Mars Pathfinder", "Viking", "ExoMars"};
	private int sondasLancadas = 0;

	private Map<Integer, Sonda> lancamentos = new HashMap<Integer, Sonda>();

	public Sonda criarNovaSonda(){
		if(sondasLancadas > (SONDAS.length - 1)){
			throw new IllegalArgumentException("A verba do projeto Mars Express chegou ao fim. Lamentamos não ter encontrado nada até agora!");
		}

		String nome = SONDAS[sondasLancadas];
		Sonda sonda = new Sonda(++sondasLancadas, nome);
		
		return sonda;
	}
	
	public void lancarSonda(Sonda sonda, Planalto planalto, Posicao posicao){
		sonda.aterrissar(planalto, posicao);
		lancamentos.put(sondasLancadas, sonda);
	}
	
	public void enviarListaDeComandos(Sonda sonda, List<Comando> comandos){
		comandos.forEach(comando -> enviarComando(sonda, comando));
	}
	
	public void enviarComando(Sonda sonda, Comando comando){
		sonda.receberComando(comando);
	}
	
	public Sonda procedimentoPadrao(Planalto planalto, Posicao posicao, List<Comando> comandos){
		Sonda sonda = criarNovaSonda();
		lancarSonda(sonda, planalto, posicao);
		
		if(comandos != null && !comandos.isEmpty()){
			enviarListaDeComandos(sonda, comandos);
		}
		
		return sonda;
	}
	
	public Posicao informarPosicao(Sonda sonda){
		return sonda.informarPosicao();
	}
	
	public List<Sonda> obterSondasLancadas(){
		List<Sonda> sondas = new ArrayList<Sonda>();
		lancamentos.forEach((i, sonda) -> {
			sondas.add(sonda);
		});
		return sondas;
	}
	
	public Sonda obterSondaLancanda(int numeroLancamento){
		return lancamentos.get(numeroLancamento);
	}
	
	public void receberInstrucoesGerais(InputStream in, OutputStream out, DecodificadorDeMensagem decodificador){
		List<String> instrucoes = decodificador.decodificarInstrucoesGerais(in);
		
		if(instrucoes.size() < 3){
			throw new IllegalArgumentException("As Instruções Gerais não foram devidamente formatadas");
		}
		
		Planalto planalto = decodificador.decodificarPlanalto(instrucoes);
		Posicao posicao = null;
		
		for(int i = 1; i < instrucoes.size(); i++){
			if(i % 2 != 0){
				posicao = decodificador.decodificarPosicao(instrucoes.get(i));
			} else {
				List<Comando> comandos = new ArrayList<Comando>();
				comandos.addAll(decodificador.decodificarComandos(instrucoes.get(i)));
				Sonda sonda = procedimentoPadrao(planalto, posicao, comandos);
				relatorioDePosicao(sonda, out);
			}
		}
	}
	
	private void relatorioDePosicao(Sonda sonda, OutputStream out){
		try {
			out.write(String.format("%s\n", sonda.informarPosicao().toString()).getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
}
