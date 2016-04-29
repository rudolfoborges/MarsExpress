package br.com.rb.marsexpress;

import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rb.marsexpress.model.ComandoCentral;

@SpringBootApplication
public class MarsExpressApplication implements CommandLineRunner {

	private @Autowired ComandoCentral comandoCentral;
	
	public static void main(String[] args) {
		SpringApplication.run(MarsExpressApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		InputStream in = MarsExpressApplication.class.getResourceAsStream("instrucoes.me");
		OutputStream out = System.out;
		comandoCentral.receberInstrucoesGerais(in, out);
		out.close();
	}
	
}
