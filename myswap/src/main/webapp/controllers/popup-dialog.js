'use strict';

angular.module('myApp.popup-dialog', ['ngDialog'])

.controller("popup-dialogCtrl", popupdialogCtrl)

function popupdialogCtrl($scope, ngDialog) {
  // $http.get('../courses/coursedata.json');
  $scope.clickToOpen = function() {
    ngDialog.open({
        template: 'views/login.html',
        className: 'ngdialog-theme-plain custom-width',
        scope: $scope
  })
}
}