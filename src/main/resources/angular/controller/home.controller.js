(function(){
	'use strict';
	
	angular
		.module('angular.me.controllers')
		.controller('HomeController', ['$scope', '$http', 'Sondas', HomeController]);
		
		
	function HomeController($scope, $http, Sondas){
		var ctrl = this;
		
		ctrl.model = {
			sonda: undefined
		};
		
		ctrl.sondas = [];
		
		ctrl.virarParaEsquerda = _virarParaEsquerda;
		ctrl.virarParaDireita = _virarParaDireita;
		ctrl.moverParaFrente = _moverParaFrente;
		ctrl.enviarNovaSonda = _enviarNovaSonda;
		
		$scope.$watch('ctrl.model.sonda', function(newValue, old){
			_obterPosicaoSonda(newValue);
		});
		
		function _init(){
			Sondas.data.forEach(function(i){
				var sonda = new models.Sonda(i.lancamento, i.nome);
				ctrl.sondas.push(sonda);
			});
		}
		
		_init();
		
		function _obterPosicaoSonda(sonda){
			if(sonda){
				sonda.obterPosicao($scope, $http).then(function(resp){
					sonda.posicaoInicial(resp.data);
				});
			}
		}
		
		function _virarParaEsquerda(){
			ctrl.model.sonda.virarParaEsquerda($scope, $http).then(function(resp){
				ctrl.model.sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _virarParaDireita(){
			ctrl.model.sonda.virarParaDireita($scope, $http).then(function(resp){
				ctrl.model.sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _moverParaFrente(){
			ctrl.model.sonda.moverParaFrente($scope, $http).then(function(resp){
				ctrl.model.sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _enviarNovaSonda(){
			var postData = {
				planaltoX: 5,
				planaltoY: 5,
				posicaoX: 0,
				posicaoY: 0,
				direcao: 'N'
			};
			$http.post($scope.apiURL('nasa/sonda'), postData).then(function(resp){
				var sonda = new models.Sonda(resp.data.lancamento, resp.data.nome)
				ctrl.sondas.push(sonda);
				$scope.$emit('$showModal', {message: 'A sonda ' + sonda.nome + ' chegou em marte'});
			});
		}
	}
	
	
})();