'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp.index', ['ngRoute'])

angular.module('myApp', [
  'ngRoute',
  'myApp.popup-dialog',
  'myApp.login',
  'dashboardCatControllers',
  'followswapCatControllers',
  'swapCatControllers',
  'itemCatControllers',
   'profileControllers',
  //'myApp.account',
  'swapStatus',
  'myApp.version'
]).

// declare all the routes for MySwap
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider
    .when('/dashboard', {
      templateUrl: 'views/dashboard.html',
      controller: 'DashboardCtrl'
    })
    .when('/followswap', {
      templateUrl: 'views/followswap.html',
      controller: 'followswapListCtrl'
    })
    .when('/followswap/:swapId', {
      templateUrl: 'views/followswap.html',
      controller: 'followswapDetailCtrl'
    })
    .when('/swaps', {
      templateUrl: 'views/swaps.html',
      controller: 'SwapListCtrl'
    })
    .when('/swaps/:swapId', {
      templateUrl: 'views/swap-detail.html',
      controller: 'SwapDetailCtrl'
    })
      .when('/items', {
      templateUrl: 'views/items.html',
      controller: 'ItemListCtrl'
    })
    .when('/items/:itemId', {
      templateUrl: 'views/item-detail.html',
      controller: 'ItemDetailCtrl'
    })
    .when('/profile-self', {
      templateUrl: 'views/profile-self.html',
      controller: 'SelfProfileCtrl'
    })
    .when('/profile-comments', {
      templateUrl: 'views/profile-comments.html',
      controller: 'CommentsCtrl'
    })
    .when('/profile-pics', {
      templateUrl: 'views/profile-pics.html',
      controller: 'PicsCtrl'
    })

    .otherwise({
      redirectTo: '/dashboard'
    });
}]);
