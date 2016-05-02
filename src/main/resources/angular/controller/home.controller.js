(function(){
	'use strict';
	
	angular
		.module('angular.me.controllers')
		.controller('HomeController', ['$scope', 'Sondas', HomeController]);
		
		
	function HomeController($scope, Sondas){
		var ctrl = this;
		
		ctrl.model = {
			sonda: undefined
		};
		
		ctrl.sondas = [];
		
		function _init(){
			Sondas.data.forEach(function(i){
				var sonda = new models.Sonda(i.lancamento, i.nome);
				ctrl.sondas.push(sonda);
			});
		}
		
		_init();
	}
	
	
})();