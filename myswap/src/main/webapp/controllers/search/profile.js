'use strict';

angular.module('profileSearchControllers', ['ngRoute', 'requeteur'])

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