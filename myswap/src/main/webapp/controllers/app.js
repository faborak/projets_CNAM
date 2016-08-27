'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp.index', ['ngRoute'])

angular.module('myApp', [
  'ngRoute',
  'myApp.popup-dialog',
  'login',
  'dashboardCatControllers',
  'followswapCatControllers',
  'swapCatControllers',
  'itemCatControllers',
  'profileControllers',
  //'myApp.account',
  'requeteur'
  
  
]).

// declare all the routes for MySwap
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider
  	.when('/login', {
	    templateUrl: 'views/login.html',
	    controller: 'loginCtrl'
	})
    .when('/dashboard', {
      templateUrl: 'views/dashboard.html',
      controller: 'DashboardCtrl'
    })
    .when('/followswap', {
      templateUrl: 'views/followswap/followswap.html',
      controller: 'followswapListCtrl'
    })
    .when('/followswap/:swapId', {
      templateUrl: 'views/followswap/followswap.html',
      controller: 'followswapDetailCtrl'
    })
    .when('/swaps', {
      templateUrl: 'views/swaps/swaps.html',
      controller: 'SwapListCtrl'
    })
    .when('/swaps/:swapId', {
      templateUrl: 'views/swaps/swap-detail.html',
      controller: 'SwapDetailCtrl'
    })
      .when('/items', {
      templateUrl: 'views/items/items.html',
      controller: 'ItemListCtrl'
    })
    .when('/items/:itemId', {
      templateUrl: 'views/items/item-detail.html',
      controller: 'ItemDetailCtrl'
    })
    .when('/new-item', {
      templateUrl: 'views/items/new-item.html',
      controller: 'NewItemCtrl'
    })
    .when('/profile-self', {
      templateUrl: 'views/user/profile-self.html',
      controller: 'SelfProfileCtrl'
    })
    .when('/profile-comments', {
      templateUrl: 'views/user/profile-comments.html',
      controller: 'CommentsCtrl'
    })
    .when('/profile-pics', {
      templateUrl: 'views/user/profile-pics.html',
      controller: 'PicsCtrl'
    })
    .otherwise({
      redirectTo: '/dashboard'
    });
}]);
