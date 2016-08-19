'use strict';

angular.module('itemCatControllers', ['ngRoute'])

.controller("ItemListCtrl", function($scope, $http, $location) {

  $scope.data = {};
  $scope.data.search = "";
  $scope.data.items = [];
  $scope.data.itemsfinal = [];

  var loadItems = function() {
    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/deal/getUserItems'
    }).success(function(data) {
      $scope.data.items = data;
    });
  };

  function filterItems(scope) {
    if ($scope.data.search != "") {
      $scope.data.itemsfinal = [];
      for (var i = 0; i < $scope.data.items.length; i++) {
        if ($scope.data.items[i].name.indexOf($scope.data.search) != -1 ||
          $scope.data.items[i].description.indexOf($scope.data.search) != -1 ||
          $scope.data.items[i].dateCreation.indexOf($scope.data.search) != -1 ||
          $scope.data.items[i].dateModification.indexOf($scope.data.search) != -1) {
          $scope.data.itemsfinal.push($scope.data.items[i]);
        }
      }
    }
    else {
      $scope.data.itemsfinal = $scope.data.items;
    }
  };

  $scope.search = function() {
    return $scope.data.search;
  };

  $scope.itemsfinal = function() {
    return $scope.data.itemsfinal;
  };

  $scope.$watch($scope.search, filterItems);

  $scope.detail = function(itemId) {
    $location.path('/items/' + itemId)
  };



  /* lancement de la recherche au départ de l'application */
  /*loadItems();

  /* Mock  */
  $scope.data.items = [{
    "IdSwapObjet": 1,
    "name": "guitare acoustique",
    "dateCreation": "12-06-2007",
    "dateModification": "12-06-2007",
    "description": "superbe guitare, très peu servie",
    "pic": null,
    "cost":122,
    "owner": {
      "id": 1,
      "lastname": "henri"
    },
    "deals": [{
      "id": 1,
      "id": 2
    }]
  }, {
    "IdSwapObjet": 2,
    "name": "guitare électrique",
    "dateCreation": "12-06-2007",
    "dateModification": "12-06-2007",
    "description": "guitare électrique ayant servi à moi, c'est dire !",
    "pic": null,
    "cost":305.5,
    "owner": {
      "id": 1,
      "lastname": "charles"
    },
    "deals": [{
      "id": 1,
      "id": 2
    }]
  }, {
    "IdSwapObjet": 3,
    "name": "chaiqe de jardin",
    "dateCreation": "12-06-2007",
    "dateModification": "12-06-2007",
    "description": "vieille chaise, quasi donnée",
    "pic": null,
    "cost":1.25,
    "owner": {
      "id": 1,
      "lastname": "henri"
    },
    "deals": [{
      "id": 1,
      "id": 2
    }]
  }];

})

.controller("ItemDetailCtrl", function($scope, $http, $routeParams, $location) {

  $scope.data = {};

  $scope.detail = function() {

    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/item/' + $routeParams.itemId
    }).success(function(data) {
      $scope.data.swap = data;
    });

  };

  $scope.retour = function() {
    $location.path('/items')
  }

  // a enelever pour democker
  /*detail();*/

  $scope.data.item = {
    "IdSwapObjet": 1,
    "name": "guitare acoustique",
    "dateCreation": "12-06-2007",
    "dateModification": "12-06-2007",
    "description": "superbe guitare, très peu servie",
    "pic": null,
    "cost":122,
    "owner": {
      "id": 1,
      "lastname": "henri"
    },
    "deals": [{
      "id": 1,
      "id": 2
    }]
  }
});