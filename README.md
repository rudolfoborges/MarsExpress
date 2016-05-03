# MarsExpress

## Teste de programação parte do processo seletivo do Elo7
	
No desafio foi utilizada a linguagem <b>Java versão 1.8</b> na estrutura de um projeto Spring Boot Maven
	
* Primeira Etapa: Para atender a primeira etapa do teste algumas classes de modelo e seus testes foram criadas representando a problema Explorando Marte
	
* Segunda Etapa: Na segunda etapa, uma camada Rest foi adicionada permitindo a interface com o mundo externo
	
## Extra:

* OAuth 2: Para garantir a segurança da camada rest
* Angular: Interface integrada a camada Rest
* Gulp: Para automatizar algumas tarefas como concatenar arquivos JavaScript, transformar HTML em templates Angular, processar arquivos SCSS e otimizar as imagens do projeto
* Bower: Facilitar o download dos libs JavaScript e CSS
* SASS: Pré-processadore CSS
	
## Comandos:
### Para obter um access_token e refresh_token manualmente</h3>
curl -X POST -k -vu marsexpress:123e4567-e89b-12d3-a456-426655440000 http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=rudolfoborges@nasa.com&password=1234&client_id=marsexpress&client_secret=123e4567-e89b-12d3-a456-426655440000&grant_type=password"
	
### Baixar os módulos Node para utilizar o Gulp
mpn install
	
### Para rodar o projeto na porta 8080
Com o Java 8 -> mvn spring-boot:run