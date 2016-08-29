'use strict';

angular.module('myApp.popup-dialog', [ 'ngDialog' ])

.controller("popup-dialogCtrl", popupdialogCtrl)

function popupdialogCtrl($scope, $location, ngDialog) {
	
	$scope.data = {};
	
	$scope.clickToOpen = function() {
		$scope.data.popup = ngDialog.open({
			template : 'views/login/login.html',
			className : 'ngdialog-theme-plain custom-width',
			scope : $scope
		})
	}
	
	
	  $scope.mySwaps = function(){
		  $location.path('/swaps') ;
	  }
	  $scope.myItems = function(){
		  $location.path('/items') ;
	  }  
	  $scope.myProfile = function(){
		  $location.path('/profile-self') ;
	  }
	  $scope.myAccount = function(){
		  $location.path('/account') ;
	  }
	  $scope.logout = function(){
		  $location.path('/disconnected') ;
	  }
}