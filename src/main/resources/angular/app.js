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