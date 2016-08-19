'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'views/login.html',
    controller: 'loginCtrl'
  });
}])

.controller("loginCtrl", function($scope, $http, $httpParamSerializer) {

  $scope.data = {};
  $scope.data.user = {"mail" : "test01@gmail.com", "password":"password01", "nom":"", "prenom":""};
  $scope.data.login= true;
  $scope.data.inscription = false;  
  $scope.data.creation = false;

  $scope.authorize = function() {
    
  // requeteur.post('http://92.90.70.23:12345/myswap/rest/authentication/authenticate',
	// data)*/

	    $http({
	        method: 'post',
	        url: 'http://localhost:8080/myswap/rest/authentication/authenticate',
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	        transformRequest:function(obj) {
	            var str = [];
	            for(var p in obj)
	            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	            return str.join("&");}, 
	        data: {        
	            mail: $scope.data.user.mail,
	            password : $scope.data.user.password}
	      }).success(function(resultat) {
	        $scope.data.token = resultat;
	        localStorage.setItem("token", $scope.data.token);
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
  
});
