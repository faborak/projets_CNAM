'use strict';

angular.module('dashboardCatControllers', ['ngRoute'])

.controller("DashboardCtrl", function($scope, $http, $location, swapStatus) {

  $scope.data = {};
  $scope.data.user = [];
  $scope.data.mailchecked = {};
  $scope.data.phonechecked = {};
  $scope.data.trades = [];
  $scope.data.news = 0;

  var findTrades = function() {
    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/deal/getUserTrades'
    }).success(function(data) {
      $scope.data.trades = data;
      for (var i = 0; i < $scope.data.trades.length; i++){
        $scope.data.trades[i].statusfinal = swapStatus.getLabelStatus($scope.data.trades[i].status);
        if ( $scope.data.trades[i].dateModifcation > $scope.data.user.lastlogin){
          $scope.data.news++;
        }
        if ( $scope.data.trades[i].initiateur.id != $scope.data.user.id){
           $scope.data.trades[i].othername = $scope.data.trades[i].initiateur.name;
        } else {
          $scope.data.trades[i].othername = $scope.data.trades[i].proposed.name;
        }
          
      }
      
    });
    
  };

  var getCurrentUser = function() {
    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(data) {
      $scope.data.user = data;
      if ($scope.data.user.mailchecked){
        $scope.data.mailchecked = "Email vérifié";
      } else {
        $scope.data.mailchecked = "envoyer un email de vérfication";
      }
      if ($scope.data.user.phonechecked){
        $scope.data.mailchecked = "Telephone vérifié";
      } else {
        $scope.data.mailchecked = "envoyer un SMS de vérfication";
      }
    });
  };

  $scope.detailTrade = function(tradeId) {
    $location.path('/swaps/' + tradeId)
  };

  $scope.detailUser = function(userId) {
    $location.path('/user/' + userId)
  };

  /* lancement de la recherche en arrivant sur l'adresse 
  findTrades();
  findUser(); */

  /* Mock  */
  $scope.data.trades = [{
    "id": 1,
    "status": 0,
    "dateModifcation": "2016-07-15",
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
    "status": 1,
    "dateModifcation": "2016-07-15",
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
    "status": 2,
    "dateModifcation": "2016-07-15",
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

  $scope.data.user = {
    "id" : 1,
    "mail": "test01@gmail.com",
    "password": "password01",
    "lastlogin": "2016-06-03",
    "lastname": "Le Gallois",
    "name": "Charle Henri",
    "mailchecked":false,
    "phonechecked":false
  };
  
  $scope.data.mailchecked = "Email vérifié";
  $scope.data.phonechecked = "Téléphone vérifié";

});