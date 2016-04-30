package br.com.rb.marsexpress.resource.request;

import java.util.ArrayList;
import java.util.List;

public class ReceberComandoRequest {

	private List<String> comandos;
	
	public ReceberComandoRequest(){
		comandos = new ArrayList<String>();
	}

	public List<String> getComandos() {
		return comandos;
	}
}
