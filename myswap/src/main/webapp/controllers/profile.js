'use strict';

angular.module('profileControllers', ['ngRoute', 'requeteur'])

.controller("SelfProfileCtrl", function($scope, data) {

  $scope.data = {};
  $scope.data.currentuser = {};
  $scope.data.currentuser.final = {};

  var startPage = function() {
		var params = {
			"token" : window.sessionStorage.getItem("token")
		};
		data.post('user/getCurrentUser', params, $scope.setCurrentUser);
  };
  
  $scope.setCurrentUser = function(data) {
	$scope.data.currentuser = data;
	$scope.data.currentuser.final = $scope.data.currentuser;
	$scope.$watch($scope.data.currentuser.final.account.phoneNumber, checkphone);
	$scope.$watch($scope.data.currentuser.final.account.mail, checkmail);
  }

  $scope.updateUser = function() {
     var params = {
        "id": $scope.data.currentuser.id,
        "name": $scope.data.currentuser.final.name,
        "lastname": $scope.data.currentuser.final.lastname,
        "email": $scope.data.currentuser.final.account.email,
        "emailChecked": $scope.data.currentuser.final.account.emailChecked,
        "phoneNumber": $scope.data.currentuser.final.account.phoneNumber,
        "phoneChecked": $scope.data.currentuser.final.account.phoneChecked,
        "street": $scope.data.currentuser.final.adress.street,
        "state": $scope.data.currentuser.final.adress.state,
        "zipcode": $scope.data.currentuser.final.adress.zipcode,
        "city": $scope.data.currentuser.final.adress.city,
        "school": $scope.data.currentuser.final.infos.school,
        "job": $scope.data.currentuser.final.infos.job,
        "about": $scope.data.currentuser.final.infos.about,
      }
     data.postAuthorize('user/update/', params, confirm);
  };

  var confirm = function(data){
	  $scope.data.currentuser = data;
	  $scope.data.currentuser.final = $scope.data.currentuser;
  }

  function checkphone(scope) {
    if ($scope.data.currentuser.final.account.phoneNumber != $scope.data.currentuser.account.phoneNumber) {
      $scope.data.currentuser.final.account.phoneChecked = false;
    }
  };

  function checkmail(scope) {
    if ($scope.data.currentuser.final.account.email != $scope.data.currentuser.account.email) {
      $scope.data.currentuser.final.account.emailChecked = false;
    }
  };

  /* lancement de la recherche au départ de l'application */
  startPage(); 

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