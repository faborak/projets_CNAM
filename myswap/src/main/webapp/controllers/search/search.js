'use strict';

angular.module('searchControllers', ['ngRoute'])

.controller("GeneralSearchCtrl", function($scope, $http) {

  $scope.data = {};
  $scope.data.keyword = "";
  $scope.data.categories = [];
  $scope.data.listeItemsTendances = [];
  $scope.data.listeItemsProposed = [];
  $scope.data.selectedCategory = "";

  var loadTendances = function() {
    var keyword = $scope.data.keyword;

    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/item/findTendances'
    }).success(function(resultat) {
      $scope.data.listeItemsTendances = resultat.data.listeItems;
    });
  };

  var loadPropositions = function() {
    /* Propositions par cookies */
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/item/findPropositions',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      params : {
        keyword: getCookie("keywordCookie")
      }
    }).success(function(resultat) {
      $scope.data.listeItemsProposed = resultat.data.listeItems;
    });
  };

  $scope.rechercher = function() {
    /* Propositions sotckées dans le serveur, pas de param en entrée */
    $http({
      method: 'get',
      url: 'http://92.90.70.23:12345/myswap/rest/item/findItems',
      params: {
        keyword: $scope.data.keyword,
        category: $scope.data.selectedCategory
      }
    }).success(function(resultat) {
      $scope.data.listeItemsProposed = resultat;
      setCookie("keywordCookie", $scope.data.keyword);
    });
  };

  function setCookie(sName, sValue) {
    var today = new Date(),
      expires = new Date();
    expires.setTime(today.getTime() + (365 * 24 * 60 * 60 * 1000));
    document.cookie = sName + "=" + encodeURIComponent(sValue) + ";expires=" + expires.toGMTString();
  }

  function getCookie(sName) {
    var cookContent = document.cookie,
      cookEnd, i, j;
    var sName = sName + "=";

    for (i = 0; i < cookContent.length; i++) {
      j = i + sName.length;
      if (cookContent.substring(i, j) == sName) {
        cookEnd = cookContent.indexOf(";", j);
        if (cookEnd == -1) {
          cookEnd = cookContent.length;
        }
        return decodeURIComponent(cookContent.substring(j, cookEnd));
      }
    }
    return null;
  }

  /* lancement des fonctions au départ de l'application */
  /*loadTendances();
  loadPropositions(); */

  /* Sorte de mock en attendant mieux  */
  /*$scope.data.categories.ajouter(new Category("cat1"));
  $scope.data.categories.ajouter(new Category("cat2"));
  $scope.data.categories.ajouter(new Category("cat3"));
  $scope.data.listeItemsTendances.ajouter(new Item(1, "tendance1", "01-08-2016", "01-08-2016", "tendance 1 mec", 0.5, ["test", "test"]));
  $scope.data.listeItemsTendances.ajouter(new Item(2, "tendance2", "01-08-2016", "01-08-2016", "tendance 2 mec", 1), ["test", "test"]);
  $scope.data.listeItemsTendances.ajouter(new Item(2, "tendance2", "01-08-2016", "01-08-2016", "tendance 3 mec", 1.5), ["test", "test"]);
*/
});

/* Modèles  */
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