'use strict';

angular
		.module('itemCatControllers', [ 'ngRoute', 'requeteur' ])

		.controller(
				"ItemListCtrl",
				function($scope, $location, data) {

					$scope.data = {};
					$scope.data.search = "";
					$scope.data.currentuser = {};
					$scope.data.items = [];
					$scope.data.itemsfinal = [];

					var startPage = function() {
						var params = {
							"token" : window.sessionStorage.getItem("token")
						};
						data.post('user/getCurrentUser', params,
								$scope.setCurrentUser);
					};

					$scope.setCurrentUser = function(data) {
						$scope.data.currentuser = data;
						$scope.data.items = $scope.data.currentuser.wholeOfItems;
					}

					function filterItems(scope) {
						if ($scope.data.search != "") {
							$scope.data.itemsfinal = [];
							for (var i = 0; i < $scope.data.items.length; i++) {
								if ($scope.data.items[i].name
										.indexOf($scope.data.search) != -1
										|| $scope.data.items[i].description
												.indexOf($scope.data.search) != -1
										|| $scope.data.items[i].dateCreation
												.indexOf($scope.data.search) != -1
										|| $scope.data.items[i].dateModification
												.indexOf($scope.data.search) != -1) {
									$scope.data.itemsfinal
											.push($scope.data.items[i]);
								}
							}
						} else {
							$scope.data.itemsfinal = $scope.data.items;
						}
					}
					;

					$scope.search = function() {
						return $scope.data.search;
					};

					$scope.itemsfinal = function() {
						return $scope.data.itemsfinal;
					};

					$scope.$watch($scope.search, filterItems);

					$scope.detail = function(itemId) {
						$location.path('/items-self/' + itemId);
					};

					$scope.newItem = function() {
						$location.path('/new-item');
					};

					/* lancement de la recherche au départ de l'application */

					startPage();
					/* Mock
					 */
					// $scope.data.items = [{
					// "IdSwapObjet": 1,
					// "name": "guitare acoustique",
					// "dateCreation": "12-06-2007",
					// "dateModification": "12-06-2007",
					// "description": "superbe guitare, très peu servie",
					// "pic": null,
					// "cost":122,
					// "owner": {
					// "id": 1,
					// "lastname": "henri"
					// },
					// "deals": [{
					// "id": 1,
					// "id": 2
					// }]
					// }, {
					// "IdSwapObjet": 2,
					// "name": "guitare électrique",
					// "dateCreation": "12-06-2007",
					// "dateModification": "12-06-2007",
					// "description": "guitare électrique ayant servi à moi,
					// c'est dire !",
					// "pic": null,
					// "cost":305.5,
					// "owner": {
					// "id": 1,
					// "lastname": "charles"
					// },
					// "deals": [{
					// "id": 1,
					// "id": 2
					// }]
					// }, {
					// "IdSwapObjet": 3,
					// "name": "chaiqe de jardin",
					// "dateCreation": "12-06-2007",
					// "dateModification": "12-06-2007",
					// "description": "vieille chaise, quasi donnée",
					// "pic": null,
					// "cost":1.25,
					// "owner": {
					// "id": 1,
					// "lastname": "henri"
					// },
					// "deals": [{
					// "id": 1,
					// "id": 2
					// }]
					// }];
				})

		.controller(
				"ItemDetailCtrl",
				function($scope, $http, $routeParams, $location) {

					$scope.data = {};

					$scope.detail = function() {

						$http(
								{
									method : 'post',
									url : 'http://92.90.70.23:12345/myswap/rest/item/'
											+ $routeParams.itemId
								}).success(function(data) {
							$scope.data.swap = data;
						});

					};

					$scope.retour = function() {
						$location.path('/items');
					}

					// a enelever pour democker
					/* detail(); */

					$scope.data.item = {
						"IdSwapObjet" : 1,
						"name" : "guitare acoustique",
						"dateCreation" : "12-06-2007",
						"dateModification" : "12-06-2007",
						"description" : "superbe guitare, très peu servie",
						"pic" : null,
						"cost" : 122,
						"owner" : {
							"id" : 1,
							"lastname" : "henri"
						},
						"deals" : [ {
							"id" : 1,
							"id" : 2
						} ]
					}
				})

		.controller(
				"SearchItemDetailCtrl",
				function($scope, $routeParams, $location, data) {

					$scope.data = {};
					$scope.data.result = false;
					$scope.data.item = [];
					$scope.userLogged = false;

					$scope.setItem = function(data) {
						if (data.length !== 0) {
							$scope.data.result = true;
							$scope.data.item = data;
						}
					}

					$scope.setLogged = function(data) {
						$scope.userLogged = data.isLogged;
					}

					$scope.data.itemFinal = function() {
						return $scope.data.item;
					};

					var startPage = function() {

						data.get('item/get/' + $routeParams.itemId, '',
								$scope.setItem);
						var params = {
							token : sessionStorage.getItem("token")
						}
						data.post('authentication/islogged', params,
								$scope.setLogged);

					};

					startPage();

					$scope.startDeal = function() {
						if ($scope.userLogged) {
							$location.path('/deal/newdeal/'
									+ $scope.data.item.idSwapObjet);
						} else {
							$location.path('/disconnected');
						}
					};

				})

		.controller("NewItemCtrl", function($scope, $http, $location, data) {

					$scope.data = {};
					$scope.data.name = '';
					$scope.data.description = '';
					$scope.data.cost = 0;
					$scope.data.currentuser = {};
					$scope.data.categories = [];
					$scope.data.selectedCategory = {};

					$scope.createItem = function() {
						var today = new Date();
						var params = {
							name : $scope.data.name,
							description : $scope.data.description,
							cost : $scope.data.cost,
							userId : $scope.data.currentuser.id,
							category : $scope.data.selectedCategory.code
						};
						data.post('item/insert', params, $scope.changePage);
					}

					$scope.changePage = function(data) {
						// retour a la page d'affichage des items
						$location.path('items');
					}

					$scope.setCurrentUser = function(data) {
						$scope.data.currentuser = data;
					}

					$scope.setCategories = function(data) {
						$scope.data.categories = data;
						$scope.data.selectedCategory = $scope.data.categories[1];
					}

					$scope.categoriesFinal = function() {
						return $scope.data.categories;
					};

					var startPage = function() {
						var params = {
							"token" : window.sessionStorage.getItem("token")
						};
						data.post('user/getCurrentUser', params,
								$scope.setCurrentUser);
						data
								.get('item/getCategories', '',
										$scope.setCategories);
					};

					var API_key = '81f578c29a5d6fb';
					var url_API_imgur = 'https://api.imgur.com/3/upload';

					startPage();

					// images
					$scope.data.img;
					$scope.data.errorupload = false;
					
					var pics = [];
					
					$scope.pics = function() {
						return pics;
					}
					
					$scope.supprimer = function(src) {
						for (var i = 0; i < pics.length; i++) {
							if (pics[i] == src){
								pics.splice(i);
							}
						}
					}
					
					$scope.data.upload = function() {
						
						var img = '';
						img = $scope.data.img;
						//img = img.replace(/data:image\/gif;base64,/g,"").replace(/data:image\/png;base64,/g,"").replace(/data:image\/jpeg;base64,/g,"").replace(/\+/g,"%2B").replace(/\//g,"%2F").replace(/=/g,"%3D");
						img = img.replace(/data:image\/gif;base64,/g,"").replace(/data:image\/png;base64,/g,"").replace(/data:image\/jpeg;base64,/g,"");
						
						
						$http({
							headers : {
								'Authorization' : 'Client-ID ' + API_key
							},
							url : url_API_imgur,
							method : 'post',
							data : {
								image : img,
								type : 'base64'
							}
						}).then(function successCallback(response) {
							pics.push(response.data.data.link);
						}, function errorCallback(err) {
							throw "error file upload";
							$scope.data.errorupload = true;
						});
						
					};
					
				});
