'use strict';

angular.module('dealsControllers', ['ngRoute', 'requeteur'])

.controller("NewDealCtrl", function($scope, $routeParams, $location, data) {

  $scope.data = {};
  $scope.data.result = false;
  
  $scope.data.otheruser = {};
  $scope.data.otheritems = [];
  $scope.data.otherdealitems = [];
  
  $scope.data.currentuser = {};
  $scope.data.currentitems = [];
  $scope.data.currendealtitems = [];
  
  $scope.setOtherItem = function(datareturn){
	  if (datareturn.length !== 0){
		  $scope.data.result = true;
		  $scope.data.otherdealitems[0] = datareturn;
		  data.get('user/getById/'+ $scope.data.otherdealitems[0].owner.id,'',$scope.setOtherUser);
	  }	  
  }
  
  $scope.setOtherUser = function(data){
	  $scope.data.otheruser = data;
	  $scope.data.otheritems = $scope.data.otheruser.wholeOfItems;
  }
  
  $scope.setCurrentUser = function(data){
	  $scope.data.currentuser = data;
	  $scope.data.currentitems = $scope.data.currentuser.wholeOfItems;
  }
  
  $scope.data.otherItemsFinal = function() {
	  return $scope.data.otheritems;
  };
  
  $scope.data.currentItemsFinal = function() {
	  return $scope.data.currentitems;
  };
  
  $scope.data.otherDealItemsFinal = function() {
	  return $scope.data.otherdealitems;
  };
  
  $scope.data.currenDealtItemsFinal = function() {
	  return $scope.data.currendealtitems;
  };
  
  $scope.data.addToDeal = function(idItem) {
	  
	  for (var i = 0; i < $scope.data.otheritems.length; i++) {
	        if ($scope.data.otheritems[i].idSwapObject == idItem) {
	          $scope.data.otherdealitems.push($scope.data.items[i]);
	          $scope.data.otheritems.splice($scope.data.otheritems[i], 1);
	        } 
	  }
	  for (var i = 0; i < $scope.data.currentitems.length; i++) {
	        if ($scope.data.currentitems[i].idSwapObject == idItem) {
	          $scope.data.currendealtitems.push($scope.data.items[i]);
	          $scope.data.currentitems.splice($scope.data.currentitems[i], 1);
	        } 
	  }
	  
  };
  
  $scope.data.createDeal = function() {
	  
	  var dealItems = [];
	  
	  for (var i = 0; i < $scope.data.otheritems.length; i++) {
	     dealItems($scope.data.otheritems[i]);
	  }
	  for (var i = 0; i < $scope.data.currentitems.length; i++) {
		  dealItems($scope.data.items[i]);
	  }
	  
	  var params = {
				"initator": $scope.data.currentuser.id,
				"proposed": $scope.data.otheruser.id,
				"swapObjects": dealItems
	  };
	  data.postAuthorize('user/getCurrentUser', params, $scope.nextPage);
  }
  
  $scope.nextPage = function(returndata){
	  if (returndata == null){
		  $location.path('disconnected');
	  } else {
		  // actuellement, cette page est dans index.html, mais est-ce utile de maintenir deux pages ?
		  $location.path('swaps');
	  }
  }
  
  var startPage = function() {
	data.get('item/get/' +$routeParams.itemId, '', $scope.setOtherItem);
	var params = {
		"token": window.sessionStorage.getItem("token")
	};
	data.post('user/getCurrentUser', params, $scope.setCurrentUser);
  };

  startPage();

});