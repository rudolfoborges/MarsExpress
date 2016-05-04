'use strict';

var models = {};

(function(){
	
	models.Sonda = function(lancamento, nome){
		this.nome = nome;
		this.lancamento = lancamento;
		this.historico = [];
	};
	
	models.Sonda.prototype.obterPosicao = function($scope, $http) {
		return $http.get($scope.apiURL('nasa/sonda/' + this.lancamento + '/posicao'));
	};
	
	models.Sonda.prototype.posicaoInicial = function(posicao){
		this.historico = [];
		this.historico.push('Sonda ' + this.nome + ' na posição X: ' + posicao.x + ', Y: ' + posicao.y + ', Direção: ' + posicao.direcao);
	};
	
	models.Sonda.prototype.virarParaEsquerda = function($scope, $http){
		return $http.put($scope.apiURL('nasa/sonda/' + this.lancamento), {comandos: ['L']});
	};
	
	models.Sonda.prototype.virarParaDireita = function($scope, $http){
		return $http.put($scope.apiURL('nasa/sonda/' + this.lancamento), {comandos: ['R']});
	};
	
	models.Sonda.prototype.moverParaFrente = function($scope, $http){
		return $http.put($scope.apiURL('nasa/sonda/' + this.lancamento), {comandos: ['M']});
	};
	
	models.Sonda.prototype.atualizarPosicao = function(posicao){
		this.historico.push('Nova posição X: ' + posicao.x + ', Y: ' + posicao.y + ', Direção: ' + posicao.direcao + ' Time: ' + new Date().getTime());
	}
	
		
})();

(function(){
	
	models.Nasa = function(){
		this.sondas = [];
	}
	
	models.Nasa.prototype.enviarNovaSonda = function($scope, $http, req){
		return $http.post($scope.apiURL('nasa/sonda'), req);
	}
	
})();