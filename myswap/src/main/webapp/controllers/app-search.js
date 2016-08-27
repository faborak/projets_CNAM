'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp.index', ['ngRoute'])

angular.module('myApp', [
  'ngRoute',
  'myApp.popup-dialog',
  'login',
  'searchControllers',
  'myApp.version',
  'profileSearchControllers',
  'itemCatControllers',
  'dealsControllers',
  
  'requeteur'
]).

// conf providers
//config(function(requeteurProvider){
//	requeteurProvider.setUrl("http://www.localhost:8080/myswap/rest/");  
//}).

// declare all the routes for MySwap
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider
  	.when('/login', {
	    templateUrl: 'views/login.html',
	    controller: 'loginCtrl'
	})
    .when('/general-search', {
      templateUrl: 'views/search/general-search.html',
      controller: 'GeneralSearchCtrl'
    })
    .when('/searchresult/:keyword/:category?', {
      templateUrl: 'views/search/search-result.html',
      controller: 'SearchResultCtrl'
    })
    .when('/item/:itemId', {
      templateUrl: 'views/search/item-detail.html',
      controller: 'SearchItemDetailCtrl'
    })
    .when('/deal/newdeal/:itemId', {
      templateUrl: 'views/search/new-deal.html',
      controller: 'NewDealCtrl'
    })
    .when('/user/profile/:userId', {
      templateUrl: 'views/search/profile.html',
      controller: 'ProfileCtrl'
    })
    .when('/disconnected', {
      templateUrl: 'views/disconnected.html',
      controller: 'DisconnectedCtrl'
    })
    .otherwise({
      redirectTo: '/general-search'
    });
}]);
