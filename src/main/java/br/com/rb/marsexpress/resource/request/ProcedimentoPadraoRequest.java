package br.com.rb.marsexpress.resource.request;

import br.com.rb.marsexpress.domain.Direcao;
import br.com.rb.marsexpress.domain.Planalto;
import br.com.rb.marsexpress.domain.Posicao;

public class ProcedimentoPadraoRequest {

	private Integer planaltoX;
	private Integer planaltoY;
	
	private Integer posicaoX;
	private Integer posicaoY;
	private String direcao;
	
	
	public Planalto getPlanalto(){
		return new Planalto(planaltoX, planaltoY);
	}
	
	public Posicao getPosicao(){
		return new Posicao(posicaoX, posicaoY, Direcao.valueOf(direcao));
	}
	
	public Integer getPlanaltoX() {
		return planaltoX;
	}
	public void setPlanaltoX(Integer planaltoX) {
		this.planaltoX = planaltoX;
	}
	public Integer getPlanaltoY() {
		return planaltoY;
	}
	public void setPlanaltoY(Integer planaltoY) {
		this.planaltoY = planaltoY;
	}
	public Integer getPosicaoX() {
		return posicaoX;
	}
	public void setPosicaoX(Integer posicaoX) {
		this.posicaoX = posicaoX;
	}
	public Integer getPosicaoY() {
		return posicaoY;
	}
	public void setPosicaoY(Integer posicaoY) {
		this.posicaoY = posicaoY;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	
}
