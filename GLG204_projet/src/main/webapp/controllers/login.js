'use strict';

angular.module('login', ['ngRoute','requeteur'])

.controller("loginCtrl", function($scope, $http, $location, data) {

  //$scope.data = {}; => défini côté popup
  $scope.data.user = {"mail" : "test@test.fr", "password":"password01"};
  $scope.data.login= true;
  $scope.data.inscription = false;  
  $scope.data.creation = false;
  $scope.data.error = false;
  $scope.data.isLogged = false;
  $scope.data.hasPicture = true;
  
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
	        $scope.data.user = data.user;
	        window.sessionStorage.setItem("token", $scope.data.token);
	        $scope.data.popup.close();
	        if (angular.isUndefined(data.user.userPictures[0])){
	        	$scope.data.hasPicture = false;
	        }
	      }).error(function(data, status, headers, config) {
		        $scope.data.isLogged = false;
		        $scope.data.error = true;
		  });
	    };
	    
  $scope.creationUser = function() {
	  
	  var params = {
			 name: $scope.data.user.name,
			 lastname: $scope.data.user.lastname,
			 email: $scope.data.user.email,
			 password: $scope.data.user.password,
			 phoneNumber: $scope.data.user.phoneNumber,
			 street: $scope.data.user.street,
			 state: $scope.data.user.state,
			 zipcode: $scope.data.user.zipcode,
			 city: $scope.data.user.city,
			};
	  data.post('user/insert', params, $scope.nextPage);  
	  
  };
  
  $scope.setLogged = function(data){
	  $scope.userLogged = data.isLogged;
  }
  
  $scope.nextPage = function(data){
	  // logon on the new account.
	  $scope.authorize;
  }
  
  var startPage = function() {
	var params= {
			token:sessionStorage.getItem("token")
	}
	data.post('authentication/islogged', params, $scope.setLogged);
  };
  
  startPage();

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
	
	var startPage = function() {
		sessionStorage.setItem('token', '');
	  };
	  
   startPage();
	
});
