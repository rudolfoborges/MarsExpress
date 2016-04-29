package br.com.rb.marsexpress.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rb.marsexpress.service.NasaService;

@RestController
@RequestMapping("nasa")
public class NasaResource {

	private NasaService nasaService;

	@Autowired
	public NasaResource(NasaService nasaService){
		this.nasaService = nasaService;
	}

}
