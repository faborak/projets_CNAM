'use strict';

angular.module('followswapCatControllers', ['ngRoute'])

.controller("followswapListCtrl", function($scope, $http, $location, swapStatus) {

  $scope.data = {};
  $scope.data.search = "";
  $scope.data.swaps = [];
  $scope.data.swapsfinal = [];

  var loadSwaps = function() {
    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/deal/getUserTrades'
    }).success(function(data) {
      $scope.data.swaps = data;
      for (var i = 0; i < $scope.data.trades.length; i++) {
        if ($scope.data.swaps[i].initiateur.id != $scope.data.user.id) {
          $scope.data.swaps[i].othername = $scope.data.swaps[i].initiateur.name;
        }
        else {
          $scope.data.swaps[i].othername = $scope.data.swaps[i].proposed.name;
        }
      }
    });
  };

  function filterSwaps(scope) {
    if ($scope.data.search != "") {
      $scope.data.swapsfinal = [];
      for (var i = 0; i < $scope.data.items.length; i++) {
        if ($scope.data.swaps[i].name.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].description.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].dateCreation.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].dateModification.indexOf($scope.data.search) != -1) {
          $scope.data.swapsfinal.push($scope.data.swaps[i]);
        }

      }
    }
    else {
      $scope.data.swapsfinal = $scope.data.swaps;
    }
  };

  $scope.search = function() {
    return $scope.data.search;
  };

  $scope.itemsfinal = function() {
    return $scope.data.swapsfinal;
  };

  $scope.$watch($scope.search, filterSwaps);

  $scope.detail = function(swapId) {
    $location.path('/followsaps/' + swapId)
  };



  /* lancement de la recherche au départ de l'application */
  /*loadSwaps();

  /* Mock  */
  $scope.data.swaps = [{
    "id": 1,
    "status": 5,
    "initiateur": {
      "id": 1,
      "name": "user 1",
      "lastname": "lastname 1"
    },
    "proposed": {
      "id": 2,
      "name": "name 2",
      "lastname": "lastname 2"
    }
  }, {
    "id": 2,
    "status": 6,
    "initiateur": {
      "id": 2,
      "name": "name 2",
      "lastname": "lastname 2"
    },
    "proposed": {
      "id": 3,
      "name": "name 3",
      "lastname": "lastname 3"
    }
  }, {
    "id": 3,
    "status": 7,
    "initiateur": {
      "id": 3,
      "name": "name 3",
      "lastname": "lastname 3"
    },
    "proposed": {
      "id": 1,
      "name": "name 1",
      "lastname": "lastname 1"
    }
  }];

})

.controller("FollowswapDetailCtrl", function($scope, $http, $routeParams, $location) {

  $scope.data = {};

  $scope.detail = function() {

    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/swap/' + $routeParams.swapId
    }).success(function(data) {
      $scope.data.swap = data;
    });

  };

  $scope.retour = function() {
    $location.path('/swaps')
  }

  // a enelever pour democker
  /*detail();*/

  $scope.data.swap = {
    "id": 1,
    "status": 0,
    "initiateur": {
      "id": 1,
      "name": "user 1",
      "lastname": "lastname 1"
    },
    "proposed": {
      "id": 2,
      "name": "name 2",
      "lastname": "lastname 2"
    },
    "deals": [{
      "id": 1,
      "dateCreation": "12-03-2016"
    }, {
      "id": 2,
      "dateCreation": "12-03-2016"
    }]
  }
});