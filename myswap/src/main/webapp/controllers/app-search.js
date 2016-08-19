'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp.index', ['ngRoute'])

angular.module('myApp', [
  'ngRoute',
  'myApp.popup-dialog',
  'myApp.login',
  'searchControllers',
  'myApp.version'
]).

// declare all the routes for MySwap
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider
    .when('/general-search', {
      templateUrl: 'views/search/general-search.html',
      controller: 'GeneralSearchCtrl'
    })
    .when('/search/:keyword', {
      templateUrl: 'views/search/search-result.html',
      controller: 'SearchResultCtrl'
    })
    .when('/item/:itemId', {
      templateUrl: 'views/search/item-detail.html',
      controller: 'ItemDetailCtrl'
    })
    .when('/search/profile/:userId', {
      templateUrl: 'views/search/search-profile.html',
      controller: 'ProfileCtrl'
    })
    .otherwise({
      redirectTo: '/general-search'
    });
}]);
