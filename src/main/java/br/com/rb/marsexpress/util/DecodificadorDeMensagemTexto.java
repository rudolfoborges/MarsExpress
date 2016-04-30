package br.com.rb.marsexpress.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.rb.marsexpress.model.Comando;
import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;

public class DecodificadorDeMensagemTexto implements DecodificadorDeMensagem {

	public Planalto decodificarPlanalto(List<String> instrucoes) {
		String[] xy = instrucoes.get(0).split(" ");
		int x = Integer.parseInt(xy[0].trim());
		int y = Integer.parseInt(xy[1].trim());
		return new Planalto(x, y);
	}

	public Posicao decodificarPosicao(String mensagem) {
		String xyDirecao[] = mensagem.split(" ");
		int x = Integer.parseInt(xyDirecao[0].trim());
		int y = Integer.parseInt(xyDirecao[1].trim());
		Direcao direcao = Direcao.valueOf(xyDirecao[2].trim());
		return new Posicao(x, y, direcao);
	}
	
	public List<Comando> decodificarComandos(String mensagem) {
		char itens[] = mensagem.trim().toCharArray();
		List<Comando> comandos = new ArrayList<Comando>();
		for (char comando : itens) {
			comandos.add(Comando.valueOf(String.valueOf(comando)));
		}
		return comandos;
	}

	public List<String> decodificarInstrucoesGerais(InputStream in) {
		List<String> instrucoes = new ArrayList<String>();
		Scanner scanner = new Scanner(in);
		while(scanner.hasNext()){
			instrucoes.add(scanner.nextLine().trim());
		}
		scanner.close();
		return instrucoes;
	}

}
