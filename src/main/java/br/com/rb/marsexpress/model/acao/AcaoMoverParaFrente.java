package br.com.rb.marsexpress.model.acao;

import br.com.rb.marsexpress.model.Direcao;
import br.com.rb.marsexpress.model.Posicao;

public class AcaoMoverParaFrente implements Acao{

	@Override
	public Posicao executar(Posicao posicao) {
		int x = posicao.getX();
		int y = posicao.getY();
		Direcao direcao = posicao.getDirecao();
		
		if(direcao.equals(Direcao.N)){
			y+=1;
		} 
		
		else if(direcao.equals(Direcao.S)) {
			y-=1;
		}
		
		else if(direcao.equals(Direcao.E)) {
			x+=1;
		}
		
		else if(direcao.equals(Direcao.W)) {
			x-=1;
		}
		
		return new Posicao(x, y, direcao);
	}

}
