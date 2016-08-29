'use strict';

angular.module('searchControllers', ['ngRoute', 'requeteur'])

.controller('GeneralSearchCtrl', function($scope, $http, data, $location) {

  $scope.data = {};
  $scope.data.keyword = "";
  $scope.data.categories = [];
  $scope.data.listeItemsTendances = [];
  $scope.data.listeItemsProposed = [];
  $scope.data.selectedCategory = "";

  $scope.setCategories = function(data){
	  $scope.data.categories = data;
	  $scope.data.selectedCategory = $scope.data.categories[1];
  }
  
  $scope.setTendances = function(data){
	  $scope.data.listeItemsTendances = data;
  }
  
  $scope.setProposed = function(data){
	  $scope.data.listeItemsProposed = data;
  }
  
  var startPage = function() {
	 var params = {
		 keyword : window.sessionStorage.getItem("keywordCookie")
	 }
	  
	 data.get('item/getCategories', '', $scope.setCategories);
	 data.post('item/getItemsByCriterias', params,$scope.setProposed); 
	 data.get('item/getTendances', params, $scope.setTendances);
  };
  
  $scope.tendancesfinal = function() {
	    return $scope.data.listeItemsTendances;
  };
  
  $scope.proposedfinal = function() {
	    return $scope.data.listeItemsProposed;
  };
   
  $scope.categoriesFinal = function() {
	    return $scope.data.categories;
  };
  
  $scope.itemDetail = function(itemId){
	  $location.path('item/'+itemId);
  }
  
  $scope.rechercher = function() {
	  window.sessionStorage.setItem("keywordCookie", $scope.data.keyword);
	  if ($scope.data.selectedCategory !== ""){
		  $location.path('/searchresult/' + $scope.data.keyword + '/'+$scope.data.selectedCategory) ;
	  } else {
		  $location.path('/searchresult/' + $scope.data.keyword) ;
	  }
  };

  startPage();
})

.controller('SearchResultCtrl', function($scope, $routeParams, $location, data) {
	
  $scope.data = {};
  $scope.data.result = false;
  $scope.data.items = [];
  $scope.data.keyword = $routeParams.keyword;
  $scope.data.selectedCategory = $routeParams.category;
  
  $scope.setItems = function(data){
	  if (data.length !== 0){
		  $scope.data.result = true;
		  $scope.data.items = data;
	  }	  
  }
  
  $scope.itemsFinal = function() {
	  return $scope.data.items;
  };
  
  $scope.itemDetail = function(itemId){
	  $location.path('item/'+itemId);
  }
  
  var startPage = function() {
	  
	var params = {
		 keyword: $scope.data.keyword,
		 category: $scope.data.selectedCategory
	};
	data.post('item/getItemsByCriterias', params, $scope.setItems);  
	  		
  };

  startPage();
  
});

/* Mod�les  */
/*
function ListeItems() {
  var list = [];
  this.ajouter = function(a) {
    list.push(a);
  }
  this.products = function() {
    return list;
  }

}

function Item(id, name, dateCreation, dateModification, description, cout, picPaths) {
  this.id = id;
  this.name = name;
  this.dateCreation = dateCreation;
  this.dateModification = dateModification;
  this.description = description;
  this.picPaths = picPaths || [];
}

function ListeCategories() {
  var list = [];
  this.ajouter = function(a) {
    list.push(a);
  }
  this.products = function() {
    return list;
  }

}

function Category(name) {
  this.name = name;
}*/