(function(){
	'use strict';
	
	angular
		.module('angular.me', ['angular.me.views', 'angular.me.controllers', 'ui.router', 'ngCookies'])
		.service('$httpInterceptor', ['$q', '$rootScope', '$log', httpInterceptor])
		.service('$oauthInterceptor', ['$q', '$rootScope', '$cookies', '$log', oauthInterceptor])
		.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', config])
		.run(['$rootScope', '$http', '$httpParamSerializer', '$cookies', '$anchorScroll', run]);
	
	angular
		.module('angular.me.controllers', []);
	
	
	function config($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider) {
		$locationProvider.html5Mode(false).hashPrefix('!');
		
		$httpProvider.interceptors.push('$httpInterceptor');
		$httpProvider.interceptors.push('$oauthInterceptor');
		
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
		    })
		    
		    .state('rudolfoborges', {
				url: '/rudolfoborges',
		      	cache: true,
		    	templateUrl: 'rudolfoborges.html'
		    })
		    
		    .state('desafio', {
				url: '/desafio',
		      	cache: true,
		    	templateUrl: 'desafio.html'
		    });
		
		$urlRouterProvider.otherwise('/home');
	}
	
	function run($rootScope, $http, $httpParamSerializer, $cookies, $anchorScroll){
		
		oauthGrant($http, $httpParamSerializer).then(function(resp){
			$cookies.put("access_token", resp.data.access_token);
			$cookies.put("refresh_token", resp.data.refresh_token);
			$http.defaults.headers.common.Authorization= 'Bearer ' + resp.data.access_token;
		});
		
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
                $rootScope.$broadcast('$showModal', {message: rejection.data.message || 'Ocorreu um erro :( Por favor, tente novamente mais tarde.'});
                return $q.reject(rejection);
            },
            response: function(response) {
                return response;
            },
            responseError: function(rejection) {
                $log.error('Response error:', rejection);
                $rootScope.$broadcast('$showModal', {message: rejection.data.message || 'Ocorreu um erro :( Por favor, tente novamente mais tarde.'});
                return $q.reject(rejection);
            }
        };
	}
	
	function oauthInterceptor($q, $rootScope, $cookies, $log){
		return {
			request: function(config) {
				if($cookies.get('access_token')) {
					config.headers.Authorization = 'Bearer ' + $cookies.get('access_token');
				}
                return config;
            }
		}
	}
	
	//Simulando um form de login com as informacoes de autenticacao do usuario
	function oauthGrant($http, $httpParamSerializer){
		var formData = {
			grant_type:'password',
			username: 'rudolfoborges@nasa.com',
			password: '1234',
			client_id: 'marsexpress',
			client_secret: '123e4567-e89b-12d3-a456-426655440000'
		};
		
		var req = {
            method: 'POST',
            url: "oauth/token",
            headers: {
                'Authorization': 'Basic ' + btoa('marsexpress:123e4567-e89b-12d3-a456-426655440000'),
                'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
            data: $httpParamSerializer(formData)
        };
		return $http(req);
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