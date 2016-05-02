(function(){
	'use strict';
	
	angular
		.module('angular.me', ['angular.me.views', 'angular.me.controllers', 'ui.router', 'LocalStorageModule'])
		.service('$httpInterceptor', ['$q', '$rootScope', '$log', httpInterceptor])
		.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', 'localStorageServiceProvider', config])
		.run(['$rootScope', '$anchorScroll', run]);
	
	angular
		.module('angular.me.controllers', []);
	
	
	function config($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider, localStorageServiceProvider) {
		$locationProvider.html5Mode(false).hashPrefix('!');
		
		$httpProvider.interceptors.push('$httpInterceptor');
		
		localStorageServiceProvider
			.setPrefix('me')
			.setStorageType('sessionStorage')
			.setNotify(true, true);
		
		$stateProvider
			.state('home', {
				url: '/home',
		      	cache: false,
		    	templateUrl: 'home.html',
		    	controller: 'HomeController as ctrl',
		    	resolve: {
		    		Sondas: function($http, $rootScope){
		    			return $http.get($rootScope.apiURL('nasa/sonda'));
		    		}
		    	}
		    });
		
		$urlRouterProvider.otherwise('/home');
	}
	
	function run($rootScope, $anchorScroll){
		
		$rootScope.$on('$showModal', function(event, data) {
			$rootScope.modal = {};
			$rootScope.modal.message = data.message;
			$('#modal').modal('show');
		});
		
		$rootScope.apiURL = function(endpoint){
			return 'api/' + endpoint;
		};
		
		$rootScope.$on("$locationChangeSuccess", function() {
            $anchorScroll();
		});
	}
	
	function httpInterceptor($q, $rootScope, $log){
		return {
            request: function(config) {
                return config;
            },
            requestError: function(rejection) {
                $log.error('Request error:', rejection);
                return $q.reject(rejection);
            },
            response: function(response) {
                return response;
            },
            responseError: function(rejection) {
                $log.error('Response error:', rejection);
                $rootScope.$broadcast('$showModal', {message: rejection.data.message});
                return $q.reject(rejection);
            }
        };
	}
	
})();
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
	}
	
	
})();