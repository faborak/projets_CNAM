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
  
  'searchControllers',
  'dealsControllers',
  
  'requeteur'
]).

// declare all the routes for MySwap
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider
  	.when('/login', {
	    templateUrl: 'views/login/login.html',
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
    .when('/dashboard', {
      templateUrl: 'views/search/dashboard.html',
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
    .when('/item/:itemId', {
      templateUrl: 'views/items/item-detail.html',
      controller: 'SearchItemDetailCtrl'
    })
    .when('/items-self/:itemId', {
      templateUrl: 'views/items/item-detail-self.html',
      controller: 'ItemDetailCtrl'
    })
    .when('/new-item', {
      templateUrl: 'views/items/new-item.html',
      controller: 'NewItemCtrl'
    })
    .when('/deal/newdeal/:itemId', {
      templateUrl: 'views/deals/new-deal.html',
      controller: 'NewDealCtrl'
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
    .when('/user/profile/:userId', {
      templateUrl: 'views/search/profile.html',
      controller: 'ProfileCtrl'
    })
    .when('/disconnected', {
      templateUrl: 'views/login/disconnected.html',
      controller: 'DisconnectedCtrl'
    })
    
    .otherwise({
      redirectTo: '/general-search'
    });
}]);
