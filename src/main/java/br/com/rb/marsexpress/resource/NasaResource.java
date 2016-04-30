package br.com.rb.marsexpress.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rb.marsexpress.model.Planalto;
import br.com.rb.marsexpress.model.Posicao;
import br.com.rb.marsexpress.model.Sonda;
import br.com.rb.marsexpress.service.NasaService;

@RestController
@RequestMapping("api/nasa")
public class NasaResource {

	private NasaService nasaService;

	@Autowired
	public NasaResource(NasaService nasaService){
		this.nasaService = nasaService;
	}
	
	
	@RequestMapping(path="sonda", method=RequestMethod.GET)
	public ResponseEntity<List<Sonda>> getSondas(){
		return null;
	}
	
	
	@RequestMapping(path="sonda/{id}", method=RequestMethod.GET)
	public ResponseEntity<Sonda> getSonda(@PathVariable("id") int id){
		return null;
	}
	
	@RequestMapping(path="sonda", method=RequestMethod.POST)
	public ResponseEntity<Sonda> postSonda(@RequestBody Map<String, Object> request){
		Planalto planalto = (Planalto) request.get("planalto");
		Posicao posicao = (Posicao) request.get("posicao");
		return null;
	}

}
