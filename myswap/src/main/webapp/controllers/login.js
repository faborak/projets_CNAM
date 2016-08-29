'use strict';

angular.module('login', ['ngRoute','requeteur'])

.controller("loginCtrl", function($scope, $http, $location, data) {

  //$scope.data = {}; => d�fini c�t� popup
  $scope.data.user = {"mail" : "usrint0001@gmail.com", "password":"password01", "nom":"", "prenom":"", "userPicture":[{"path":"test"}]};
  $scope.data.login= true;
  $scope.data.inscription = false;  
  $scope.data.creation = false;
  $scope.data.error = false;
  $scope.data.isLogged = false;
  
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
	      }).error(function(data, status, headers, config) {
		        $scope.data.isLogged = false;
		        $scope.data.error = true;
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
  
  $scope.setLogged = function(data){
	  $scope.userLogged = data.isLogged;
  }
  
  var startPage = function() {
	var params= {
			token:sessionStorage.getItem("token")
	}
	data.post('authentication/islogged', params, $scope.setLogged);
  };
  
//  startPage();

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
