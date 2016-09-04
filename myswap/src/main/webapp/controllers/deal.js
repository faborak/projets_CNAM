'use strict';

angular.module('dealsControllers', ['ngRoute', 'requeteur'])

.controller("NewDealCtrl", function($scope, $routeParams, $location, data) {

  $scope.data = {};
  $scope.data.result = false;
  $scope.data.total = 0;
  
  $scope.data.otheruser = {};
  $scope.data.otheritems = [];
  $scope.data.otherdealitems = [];
  
  $scope.data.currentuser = {};
  $scope.data.currentitems = [];
  $scope.data.currentdealitems = [];
  
  $scope.itemDetail = function(itemId){
	  $location.path('item/'+itemId);
  }
  
  $scope.userDetail = function(userId){
	  $location.path('profile/'+userId);
  }
  
  $scope.setOtherItem = function(datareturn){
	  if (datareturn.length !== 0){
		  $scope.data.result = true;
		  $scope.data.otherdealitems[0] = datareturn;
		  data.get('user/getUserByItem/'+ $scope.data.otherdealitems[0].idSwapObjet,'',$scope.setOtherUser);
		  computeTotal();
	  }	  
  }
  
  $scope.setOtherUser = function(datareturn){
	  $scope.data.otheruser = datareturn;
	  data.get('item/getItemsByUser/'+$scope.data.otheruser.id,'',$scope.setOtherItems);
  }
  
  $scope.setOtherItems = function(data){
	  $scope.data.otheritems = data;
	  for (var i = 0; i < $scope.data.otheritems.length; i++) {
	        if ($scope.data.otheritems[i].idSwapObjet == $scope.data.otherdealitems[0].idSwapObjet) {
	          $scope.data.otheritems.splice(i, 1);
	        } 
	  }
  }
  
  $scope.setCurrentUser = function(datareturn){
	  $scope.data.currentuser = datareturn;
	  data.get('item/getItemsByUser/'+$scope.data.currentuser.id,'',$scope.setCurrentItems);
  }
  
  $scope.setCurrentItems = function(data){
	  $scope.data.currentitems = data;
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
	  return $scope.data.currentdealitems;
  };
  
  $scope.addToDeal = function(idItem) {
	  
	  for (var i = 0; i < $scope.data.otheritems.length; i++) {
	        if ($scope.data.otheritems[i].idSwapObjet == idItem) {
	          $scope.data.otherdealitems.push($scope.data.otheritems[i]);
	          $scope.data.otheritems.splice(i, 1);
	        } 
	  }
	  for (var i = 0; i < $scope.data.currentitems.length; i++) {
	        if ($scope.data.currentitems[i].idSwapObjet == idItem) {
	          $scope.data.currentdealitems.push($scope.data.currentitems[i]);
	          $scope.data.currentitems.splice(i, 1);
	        } 
	  }
	  
	  computeTotal();
  };
  
  $scope.removeFromDeal = function(idItem) {
	  
	  for (var i = 0; i < $scope.data.otherdealitems.length; i++) {
	        if ($scope.data.otherdealitems[i].idSwapObjet == idItem) {
	          $scope.data.otheritems.push($scope.data.otherdealitems[i]);
	          $scope.data.otherdealitems.splice(i, 1);
	        } 
	  }
	  for (var i = 0; i < $scope.data.currentdealitems.length; i++) {
	        if ($scope.data.currentdealitems[i].idSwapObjet == idItem) {
	          $scope.data.currentitems.push($scope.data.currentdealitems[i]);
	          $scope.data.currentdealitems.splice(i, 1);
	        } 
	  }
	  
	  computeTotal();
	  
  }

  
  var computeTotal = function(){
	  $scope.data.total = 0;
	  for (var i = 0; i < $scope.data.otherdealitems.length; i++) {
	        $scope.data.total -=  $scope.data.otherdealitems[i].cost;
	  }
	  for (var i = 0; i < $scope.data.currentdealitems.length; i++) {
		  $scope.data.total +=  $scope.data.currentdealitems[i].cost;
	  }
  }
  
  $scope.nextPage = function(returndata){
	  if (returndata == null){
		  $location.path('disconnected');
	  } else {
		  
		 var dealItems = [];
			
	    for (var i = 0; i < $scope.data.otherdealitems.length; i++) {
		    dealItems.push($scope.data.otherdealitems[i].idSwapObjet);
		}
	    for (var i = 0; i < $scope.data.currentdealitems.length; i++) {
		    dealItems.push($scope.data.currentdealitems[i].idSwapObjet);
	    }
		
		var params = {
			"initator": $scope.data.currentuser.id,
			"proposed": $scope.data.otheruser.id,
			"swapObjects": dealItems
		}	
		  
		 data.postAuthorize('deal/insert', params, $scope.finishDeal);
		 
	  }
  }
  
  $scope.finishDeal = function(data){
	  if (data != null){
		  $location.path('swaps');
	  } else {
		  throw "insert failed";
	  }
	  
  }
  
  
  $scope.createDeal = function() {
	var params = {	
				"token": window.sessionStorage.getItem("token")
	};
	  data.postAuthorize('user/getCurrentUser', params, $scope.nextPage);
	 
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