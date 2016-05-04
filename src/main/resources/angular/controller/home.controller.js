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
		
		ctrl.nasa = new models.Nasa();
		
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
				ctrl.nasa.sondas.push(sonda);
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
			var sonda = ctrl.model.sonda;
			sonda.virarParaEsquerda($scope, $http).then(function(resp){
				sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _virarParaDireita(){
			var sonda = ctrl.model.sonda;
			sonda.virarParaDireita($scope, $http).then(function(resp){
				sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _moverParaFrente(){
			var sonda = ctrl.model.sonda;
			sonda.moverParaFrente($scope, $http).then(function(resp){
				sonda.atualizarPosicao(resp.data);
			});
		}
		
		function _enviarNovaSonda(){
			var req = {
				planaltoX: 5,
				planaltoY: 5,
				posicaoX: 0,
				posicaoY: 0,
				direcao: 'N'
			};
			
			
			ctrl.nasa.enviarNovaSonda($scope, $http, req).then(function(resp){
				var sonda = new models.Sonda(resp.data.lancamento, resp.data.nome)
				ctrl.nasa.sondas.push(sonda);
				$scope.$emit('$showModal', {message: 'A sonda ' + sonda.nome + ' chegou em marte'});
			});
		}
	}
	
	
})();