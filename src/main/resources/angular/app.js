(function(){
	'use strict';
	
	angular
		.module('angular.me', ['angular.me.views', 'angular.me.controllers', 'ui.router'])
		.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', config]);
	
	angular
		.module('angular.me.controllers', []);
	
	
	function config($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider) {
		$locationProvider.html5Mode(false).hashPrefix('!');
		
		$stateProvider
		
			.state('home', {
				url: '/home',
		      	cache: false,
		    	templateUrl: 'home.html',
		    });
		
		$urlRouterProvider.otherwise('/home');
	}
		
		
	
})();