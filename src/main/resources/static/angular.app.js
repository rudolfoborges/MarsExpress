!function(){"use strict";function r(r,e,o,t,n){r.html5Mode(!1).hashPrefix("!"),t.interceptors.push("$httpInterceptor"),n.setPrefix("me").setStorageType("sessionStorage").setNotify(!0,!0),e.state("home",{url:"/home",cache:!1,templateUrl:"home.html"}),o.otherwise("/home")}function e(r,e){r.$on("$locationChangeSuccess",function(){e()})}function o(r,e,o){return{request:function(r){return r},requestError:function(e){return o.error("Request error:",e),r.reject(e)},response:function(r){return r},responseError:function(e){return o.error("Response error:",e),r.reject(e)}}}angular.module("angular.me",["angular.me.views","angular.me.controllers","ui.router","LocalStorageModule"]).service("$httpInterceptor",["$q","$rootScope","$log",o]).config(["$locationProvider","$stateProvider","$urlRouterProvider","$httpProvider","localStorageServiceProvider",r]).run(["$rootScope","$anchorScroll",e]),angular.module("angular.me.controllers",[])}(),function(){"use strict";function r(r){}angular.module("angular.me.controllers").controller("HomeController",["$scope",r])}();