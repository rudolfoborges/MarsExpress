package br.com.rb.marsexpress.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		List<Sonda> sondas = nasaService.obterSondasLancadas();
		return new ResponseEntity<List<Sonda>>(sondas, HttpStatus.OK);
	}
	
	@RequestMapping(path="sonda/{sonda}", method=RequestMethod.GET)
	public ResponseEntity<Sonda> getSonda(@PathVariable("sonda") int sondaId){
		Sonda sonda = nasaService.obterSondaLancanda(sondaId);
		return new ResponseEntity<Sonda>(sonda, HttpStatus.OK);
	}
	
	@RequestMapping(path="sonda", method=RequestMethod.POST)
	public ResponseEntity<Sonda> postSonda(@RequestBody Map<String, Object> request){
		Planalto planalto = (Planalto) request.get("planalto");
		Posicao posicao = (Posicao) request.get("posicao");
		Sonda sonda = nasaService.procedimentoPadrao(planalto, posicao, null);
		return new ResponseEntity<Sonda>(sonda, HttpStatus.OK);
	}
	
	@RequestMapping(path="sonda/{sonda}", method=RequestMethod.PUT)
	public ResponseEntity<Posicao> putReceberComandos(@PathVariable("sonda") int sondaId, List<String> comandos){
		Sonda sonda = nasaService.obterSondaLancanda(sondaId);
		return null;
	}
	
	@RequestMapping(path="sonda/{sonda}/posicao", method=RequestMethod.GET)
	public ResponseEntity<Posicao> getSondaPosicao(@PathVariable("sonda") int sondaId){
		Sonda sonda = nasaService.obterSondaLancanda(sondaId);
		Posicao posicao = sonda.informarPosicao();
		return new ResponseEntity<Posicao>(posicao, HttpStatus.OK);
	}

}
