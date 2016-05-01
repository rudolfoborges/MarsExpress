package br.com.rb.marsexpress;

import java.io.InputStream;
import java.io.OutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rb.marsexpress.model.Funcionario;
import br.com.rb.marsexpress.repository.FuncionarioRepository;
import br.com.rb.marsexpress.service.NasaService;
import br.com.rb.marsexpress.util.DecodificadorDeMensagemTexto;

@SpringBootApplication
public class MarsExpressApplication implements CommandLineRunner {
	
	private @Autowired NasaService nasaService;
	private @Autowired FuncionarioRepository funcionarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MarsExpressApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		cadastrarFuncionariosNasa();
		desafioElo7();
	}
	
	public void desafioElo7() throws Exception {
		InputStream in = MarsExpressApplication.class.getResourceAsStream("instrucoes.me");
		OutputStream out = System.out;
		nasaService.receberInstrucoesGerais(in, out, new DecodificadorDeMensagemTexto());
		out.close();
	}
	
	@Transactional
	public void cadastrarFuncionariosNasa(){
		new Funcionario("Rudolfo Borges", "rudolfoborges@nasa.com", "1234").salvar(funcionarioRepository);
		new Funcionario("Fulano", "xpto@nasa.com", "1234").salvar(funcionarioRepository);
	}
	
}
