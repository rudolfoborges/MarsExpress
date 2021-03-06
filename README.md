# MarsExpress - Teste de programação

No desafio foi utilizada a linguagem <b>Java versão 1.8</b> na estrutura de um projeto Spring Boot Maven
	
* Primeira Etapa: Para atender a primeira etapa do teste algumas classes de modelo e seus testes foram criadas representando a problema Explorando Marte
	
* Segunda Etapa: Na segunda etapa, uma camada Rest foi adicionada permitindo a interface com o mundo externo

## Problema -Explorando Marte

Um conjunto de sondas foram enviadas pela NASA a Marte e irá pousar num planalto. Esse planalto, que curiosamente é retangular, deve ser explorado pelas sondas para que suas câmeras embutidas consigam ter uma visão completa da área e enviar as imagens de volta para a Terra.

A posição e direção de uma sonda são representadas por uma combinação de coordenadas x-y e uma letra representando a direção cardinal para qual a sonda aponta, seguindo a rosa dos ventos.

-------norte

oeste---------leste

--------sul
       
O planalto é divido numa malha para simplificar a navegação. Um exemplo de posição seria (0, 0, N), que indica que a sonda está no canto inferior esquerdo e apontando para o Norte.

Para controlar as sondas, a NASA envia uma simples sequência de letras. As letras possíveis são "E", "D" e "M". Destas, "E" e "D" fazem a sonda virar 90 graus para a esquerda ou direita, respectivamente, sem mover a sonda. "M" faz com que a sonda mova-se para a frente um ponto da malha, mantendo a mesma direção.
	
## Extra:

* OAuth 2: Para garantir a segurança da camada rest
* Angular: Interface integrada a camada Rest
* Gulp: Para automatizar algumas tarefas como concatenar arquivos JavaScript, transformar HTML em templates Angular, processar arquivos SCSS e otimizar as imagens do projeto
* Bower: Facilitar o download dos libs JavaScript e CSS
* SASS: Pré-processadore CSS
* Docker: Build utilizando o Docker com Dockerfile
 
## Gulp:

* ***/**.js -> angular.app.js
* ***/**.html -> angular.views.js
* ***/**.scss -> style.css
* normal-images -> static/assets/images

## Docker Build

* cd project_home
* mvn clean package
* docker build -t mars/rudolfoborges .
* docker run -t -p 8080:8080 --name mars mars/rudolfoborges

	
## Comandos:
### Para obter um access_token e refresh_token manualmente</h3>
curl -X POST -k -vu marsexpress:123e4567-e89b-12d3-a456-426655440000 http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=rudolfoborges@nasa.com&password=1234&client_id=marsexpress&client_secret=123e4567-e89b-12d3-a456-426655440000&grant_type=password"
	
### Baixar os módulos Node para utilizar o Gulp
mpn install
	
## Interface

![alt text](https://github.com/rudolfoborges/MarsExpress/blob/master/marsexpress.png "Mars Express")
