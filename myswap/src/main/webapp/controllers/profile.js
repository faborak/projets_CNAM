'use strict';

angular.module('profileControllers', ['ngRoute', 'requeteur'])

.controller("SelfProfileCtrl", function($scope, $http, $location) {

  $scope.data = {};
  $scope.data.user = {};
  $scope.data.user.final = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
      $scope.data.user.final = resultat;
    });
  };

  $scope.updateUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/updateUser',
      params: {
        "id": $scope.data.user.id,
        "name": $scope.data.user.final.name,
        "lastname": $scope.data.user.final.lastname,
        "email": $scope.data.user.final.account.email,
        "emailChecked": $scope.data.user.final.account.emailChecked,
        "phoneNumber": $scope.data.user.final.account.phoneNumber,
        "phoneChecked": $scope.data.user.final.account.phoneChecked,
        "street": $scope.data.user.final.adress.street,
        "state": $scope.data.user.final.adress.state,
        "zipcode": $scope.data.user.final.adress.zipcode,
        "city": $scope.data.user.final.adress.city,
        "pics": $scope.data.user.final.pics,
        "school": $scope.data.user.final.infos.school,
        "job": $scope.data.user.final.infos.job,
        "about": $scope.data.user.final.infos.about,
      }
    }).success(function(data) {
      $scope.data.user = data;
    });
  };



  function checkphone(scope) {
    if ($scope.data.user.final.account.phoneNumber != $scope.data.user.account.phoneNumber) {
      $scope.data.user.final.account.phoneChecked = false;
    }
  };

  function checkmail(scope) {
    if ($scope.data.user.final.account.email != $scope.data.user.account.email) {
      $scope.data.user.final.account.emailChecked = false;
    }
  };

  /* lancement de la recherche au d�part de l'application */
  //getCurrentUser();

  $scope.data.user.final = $scope.data.user;

  $scope.$watch($scope.data.user.final.account.phoneNumber, checkphone);
  $scope.$watch($scope.data.user.final.account.mail, checkmail);

})


.controller("CommentsCtrl", function($scope, $http) {

  $scope.data = {};
  $scope.data.user = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
    });
  };

  $scope.comments = function() {
    return $scope.data.user.commentsOnUser;
  };

  // a enelever pour democker
  /*getCurrentUser();*/

})

.controller("PicsCtrl", function($scope, $http) {

  $scope.data = {};
  $scope.data.user = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
    });
  };

  $scope.comments = function() {
    return $scope.data.user.commentsOnUser;
  };

//  $scope.ajouter = function() {
//    var fichier = document.getElementById('fichier').files[0];
//    var lecture = new FileReader();
//    lecture.onloadend = function(evenement) {
//      var donnees = evenement.target.result;
//    }
//    lecture.readAsBinaryString(fichier);
//  }

  $scope.pics = function() {
    return $scope.data.user.pics;
  };

//  $scope.supprimer = function(src) {
//    for (var i = 0; i < $scope.data.user.pics.length; i += 1) {
//      if ($scope.data.user.pic[i] === src) {
//        $scope.data.user.pic[i].delete;
//        $scope.updateUser($scope.data.user);
//      }
//    }
//  };

  $scope.updateUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/updateUser',
      params: {
        "id": $scope.data.user.id,
        "name": $scope.data.user.final.name,
        "lastname": $scope.data.user.final.lastname,
        "email": $scope.data.user.final.account.email,
        "emailChecked": $scope.data.user.final.account.emailChecked,
        "phoneNumber": $scope.data.user.final.account.phoneNumber,
        "phoneChecked": $scope.data.user.final.account.phoneChecked,
        "street": $scope.data.user.final.adress.street,
        "state": $scope.data.user.final.adress.state,
        "zipcode": $scope.data.user.final.adress.zipcode,
        "city": $scope.data.user.final.adress.city,
        "pics": $scope.data.user.final.pics,
        "school": $scope.data.user.final.infos.school,
        "job": $scope.data.user.final.infos.job,
        "about": $scope.data.user.final.infos.about,
      }
    }).success(function(resultat) {
      //$scope.resultat = resultat;
    });
  };

})

.controller("ProfileCtrl", function($scope, $routeParams, data) {

  $scope.data = {};
  $scope.data.result = false;
  $scope.data.user = [];
  
  $scope.setUser = function(data){
	  if (data.length !== 0){
		  $scope.data.result = true;
		  $scope.data.user = data;
	  }	  
  }
  
  $scope.data.userFinal = function() {
	  return $scope.data.user;
  };
  
  var startPage = function() {
	  
	data.get('user/getById/' +$routeParams.userId, '', $scope.setUser);  
	  		
  };

  startPage();

});