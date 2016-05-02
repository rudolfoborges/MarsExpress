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
		    });
		
		$urlRouterProvider.otherwise('/home');
	}
	
	function run($rootScope, $anchorScroll){
		
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
                return $q.reject(rejection);
            }
        };
	}
	
})();