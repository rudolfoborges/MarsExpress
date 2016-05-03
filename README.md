# MarsExpress

<h1>Teste de programação parte do processo seletivo do Elo7</h1>
	
	<p>No desafio foi utilizada a linguagem <b>Java versão 1.8</b> na estrutura de um projeto Spring Boot Maven</p>
	
	<p>Primeira Etapa: Para atender a primeira etapa do teste algumas classes de modelo e seus testes foram criadas representando a problema Explorando Marte</p>
	
	<p>Segunda Etapa: Na segunda etapa, uma camada Rest foi adicionada permitindo a interface com o mundo externo</p>
	
	<h2>Extra:</h2>
	
	<ul>
		<li>OAuth 2: Para garantir a segurança da camada rest</li>
		<li>Angular: Interface integrada a camada Rest</li>
		<li>Gulp: Para automatizar algumas tarefas como concatenar arquivos JavaScript, transformar HTML em templates Angular, processar arquivos SCSS e otimizar as imagens do projeto</li>
		<li>Bower: Facilitar o download dos libs JavaScript e CSS</li>
		<li>SASS: Pré-processadore CSS</li>
	</ul>
	
	<h2>Comandos:</h2>
	<h3>Para obter um access_token e refresh_token manualmente</h3>
	<p>curl -X POST -k -vu marsexpress:123e4567-e89b-12d3-a456-426655440000 http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=rudolfoborges@nasa.com&password=1234&client_id=marsexpress&client_secret=123e4567-e89b-12d3-a456-426655440000&grant_type=password"</p>
	
	<h3>Baixar os módulos Node para utilizar o Gulp</h3>
	<p>mpn install</p>
	
	<h3>Para rodar o projeto na porta 8080</h3>
	<p>Com o Java 8 -> mvn spring-boot:run</p>