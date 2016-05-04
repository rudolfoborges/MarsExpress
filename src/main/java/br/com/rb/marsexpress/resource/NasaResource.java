package br.com.rb.marsexpress.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rb.marsexpress.domain.Comando;
import br.com.rb.marsexpress.domain.Posicao;
import br.com.rb.marsexpress.domain.Sonda;
import br.com.rb.marsexpress.resource.request.ProcedimentoPadraoRequest;
import br.com.rb.marsexpress.resource.request.ReceberComandoRequest;
import br.com.rb.marsexpress.service.NasaService;
import br.com.rb.marsexpress.util.DecodificadorDeMensagem;

@RestController
@RequestMapping("api/nasa")
public class NasaResource {

	private final NasaService nasaService;
	private final DecodificadorDeMensagem decodificador;

	@Autowired
	public NasaResource(NasaService nasaService, @Qualifier("rest") DecodificadorDeMensagem decodificador){
		this.nasaService = nasaService;
		this.decodificador = decodificador;
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
	public ResponseEntity<Sonda> postProcedimentoPadrao(@RequestBody ProcedimentoPadraoRequest request){
		Sonda sonda = nasaService.procedimentoPadrao(request.getPlanalto(), request.getPosicao(), null);
		return new ResponseEntity<Sonda>(sonda, HttpStatus.OK);
	}
	
	@RequestMapping(path="sonda/{sonda}", method=RequestMethod.PUT)
	public ResponseEntity<Posicao> putReceberComandos(@PathVariable("sonda") int sondaId, @RequestBody ReceberComandoRequest request){
		List<Comando> comandos = decodificador.decodificarComandos(request.getComandos());
		Sonda sonda = nasaService.obterSondaLancanda(sondaId);
		nasaService.enviarListaDeComandos(sonda, comandos);
		Posicao posicao = nasaService.informarPosicao(sonda);
		return new ResponseEntity<Posicao>(posicao, HttpStatus.OK);
	}
	
	@RequestMapping(path="sonda/{sonda}/posicao", method=RequestMethod.GET)
	public ResponseEntity<Posicao> getSondaPosicao(@PathVariable("sonda") int sondaId){
		Sonda sonda = nasaService.obterSondaLancanda(sondaId);
		Posicao posicao = nasaService.informarPosicao(sonda);
		return new ResponseEntity<Posicao>(posicao, HttpStatus.OK);
	}

}
