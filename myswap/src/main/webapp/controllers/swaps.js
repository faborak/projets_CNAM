'use strict';

angular.module('swapCatControllers', ['ngRoute'])

.controller("SwapListCtrl", function($scope, $http, $location) {

  $scope.data = {};
  $scope.data.search = "";
  $scope.data.swaps = [];
  $scope.data.swapsfinal = [];

  var loadSwaps = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/deal/getDealsUser'
    }).success(function(resultat) {
      $scope.data.swaps = resultat.swaps;
    });
  };

  function filterSwaps(scope) {
    if ($scope.data.search != "") {
      $scope.data.swapsfinal = [];
      for (var i = 0; i < $scope.data.swaps.length; i++) {
        if ($scope.data.swaps[i].initiateur.name.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].initiateur.lastname.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].proposed.name.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].proposed.lastname.indexOf($scope.data.search) != -1) {
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

  $scope.swapsfinal = function() {
    return $scope.data.swapsfinal;
  };

  $scope.$watch($scope.search, filterSwaps);
  
   $scope.detail = function(swapId) {
      $location.path('/swaps/'+swapId)
  };



  /* lancement de la recherche au départ de l'application */
  /*loadSwaps();

  /* Mock  */
  $scope.data.swaps = [{
    "id": 1,
    "status": "En attente de réponse de votre part",
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
    "status": "En attente de retour de votre interlocuteur",
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
    "status": "Nouvelle proposition à valider",
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


.controller("SwapDetailCtrl", function($scope, $http, $routeParams, $location, swapStatus) {

  $scope.data = {};

  $scope.detail = function() {

    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/deal/' + $routeParams.swapId
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
    "status": "En attente de réponse de votre part",
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