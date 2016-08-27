'use strict';

angular.module('login', ['ngRoute'])

.controller("loginCtrl", function($scope, $http, $httpParamSerializer) {

  //$scope.data = {}; => défini côté popup
  $scope.data.user = {"mail" : "test1@gmail.com", "password":"password01", "nom":"", "prenom":""};
  $scope.data.login= true;
  $scope.data.inscription = false;  
  $scope.data.creation = false;

  $scope.authorize = function() {
	    $http({
	        method: 'post',
	        url: 'http://www.localhost:8080/myswap/rest/authentication/authenticate',
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	        transformRequest:function(obj) {
	            var str = [];
	            for(var p in obj)
	            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	            return str.join("&");}, 
	        data: {        
	            mail: $scope.data.user.mail,
	            password : $scope.data.user.password}
	      }).success(function(data, status, headers, config) {
	        $scope.data.isLogged = true;
	        $scope.data.token = data.token;
	        window.sessionStorage.setItem("token", $scope.data.token);
	        $scope.data.popup.close();
	      });
	    };
  
  $scope.creationUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/insertUser',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      params: {
        token : localStorage.getItem("token"),
        lastname: $scope.data.user.lastname,
        name: $scope.data.user.name,
        street: $scope.data.user.street,
        state: $scope.data.user.state,
        zipcode: $scope.data.user.zipcode,
        city: $scope.data.user.city,
        mail: $scope.data.user.mail,
        password : $scope.data.user.password
      }
    }).success(function(resultat) {
      $scope.data.resultat = resultat.resultat;
    });
  };

  $scope.pageCreation = function(){
    $scope.data.login = false;
    $scope.data.inscription = false;  
    $scope.data.creation = true;
  }

  $scope.pageInscription = function(){
    $scope.data.login = false;
    $scope.data.inscription = true;  
    $scope.data.creation = false;
  }
  
})

.controller("DisconnectedCtrl", function($scope, $http, $httpParamSerializer) {
	
});
