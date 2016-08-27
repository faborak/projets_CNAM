'use strict';

angular.module('myApp.popup-dialog', [ 'ngDialog' ])

.controller("popup-dialogCtrl", popupdialogCtrl)

function popupdialogCtrl($scope, ngDialog) {
	
	$scope.data = {};
	
	$scope.clickToOpen = function() {
		$scope.data.popup = ngDialog.open({
			template : 'views/login.html',
			className : 'ngdialog-theme-plain custom-width',
			scope : $scope
		})
	}
}