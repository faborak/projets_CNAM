'use strict';

angular.module('profileSearchControllers', ['ngRoute'])

.controller("ProfileCtrl", function($scope, $http, $routeParams, $location) {

  $scope.data = {};

  $scope.detail = function() {

    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/item/' + $routeParams.itemId
    }).success(function(data) {
      $scope.data.swap = data;
    });

  };



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