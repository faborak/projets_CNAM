'use strict';

angular.module('profileControllers', ['ngRoute', 'requeteur'])

.controller("SelfProfileCtrl", function($scope, data) {

  $scope.data = {};
  $scope.data.currentuser = {};
  $scope.data.currentuser.final = {};

  var startPage = function() {
		var params = {
			"token" : window.sessionStorage.getItem("token")
		};
		data.post('user/getCurrentUser', params, $scope.setCurrentUser);
		data.post('authentication/islogged', params, $scope.setLogged);
	};
	
  $scope.setLogged = function(data) {
	if (data == false){
		$location.path('/disconnected');
	}
  }	
  
  $scope.setCurrentUser = function(data) {
	$scope.data.currentuser = data;
	$scope.data.currentuser.final = $scope.data.currentuser;
	$scope.$watch($scope.data.currentuser.final.account.phoneNumber, checkphone);
	$scope.$watch($scope.data.currentuser.final.account.mail, checkmail);
  }

  $scope.updateUser = function() {
     var params = {
        "id": $scope.data.currentuser.id,
        "name": $scope.data.currentuser.final.name,
        "lastname": $scope.data.currentuser.final.lastname,
        "email": $scope.data.currentuser.final.account.email,
        "emailChecked": $scope.data.currentuser.final.account.emailChecked,
        "phoneNumber": $scope.data.currentuser.final.account.phoneNumber,
        "phoneChecked": $scope.data.currentuser.final.account.phoneChecked,
        "street": $scope.data.currentuser.final.adress.street,
        "state": $scope.data.currentuser.final.adress.state,
        "zipcode": $scope.data.currentuser.final.adress.zipcode,
        "city": $scope.data.currentuser.final.adress.city,
        "school": $scope.data.currentuser.final.infos.school,
        "job": $scope.data.currentuser.final.infos.job,
        "about": $scope.data.currentuser.final.infos.about,
      }
     data.postAuthorize('user/update/', params, confirm);
  };

  var confirm = function(data){
	  $scope.data.currentuser = data;
	  $scope.data.currentuser.final = $scope.data.currentuser;
  }

  function checkphone(scope) {
    if ($scope.data.currentuser.final.account.phoneNumber != $scope.data.currentuser.account.phoneNumber) {
      $scope.data.currentuser.final.account.phoneChecked = false;
    }
  };

  function checkmail(scope) {
    if ($scope.data.currentuser.final.account.email != $scope.data.currentuser.account.email) {
      $scope.data.currentuser.final.account.emailChecked = false;
    }
  };

  /* lancement de la recherche au départ de l'application */
  startPage(); 

})


.controller("CommentsCtrl", function($scope, $location, data) {

  $scope.data = {};
  $scope.data.currentuser = {};
  $scope.data.currentComments = {}; 
  
  var startPage = function() {
	var params = {
		"token" : window.sessionStorage.getItem("token")
	};
	data.post('user/getCurrentUser', params, $scope.setCurrentUser);
  };

  $scope.setCurrentUser = function(datareturn){
	  $scope.data.currentuser = datareturn;
	  data.get('comment/getByUser/'+$scope.data.currentuser.id,'',$scope.setCurrentComments);
  }
  
  $scope.setCurrentComments = function(data){
	  $scope.data.currentComments = data;
	  for (var i = 0; i <  $scope.data.currentComments.lenght; i++){
		  if ($scope.data.currentComments[i].noting.id == $scope.data.currentuser.id){
			  $scope.data.currentComments[i].splice(i, 1);
		  }
	  }
  }
  
  $scope.userDetail = function(userId) {
	 $location.path('/user/' + userId)
  };

//  $scope.comments = function() {
//    return $scope.data.user.commentsOnUser;
//  };

  startPage();

})

.controller("PicsCtrl", function($scope, $http, data) {

  $scope.data = {};
  $scope.data.currentuser = {};

  var startPage = function() {
	var params = {
			"token" : window.sessionStorage.getItem("token")
		};
		data.post('user/getCurrentUser', params, $scope.setCurrentUser);
	  };

	  $scope.setCurrentUser = function(datareturn){
		  $scope.data.currentuser = datareturn;
	  }

  var insertPics = function(path) {
			var paramsPicture = {
				picPath: path,
				userId: $scope.data.currentuser.id
			};
		data.post('user/insertPicture', paramsPicture, startPage);	
	};
  
    //pics uploaded to partner Imgur

	var API_key = '81f578c29a5d6fb';
	var url_API_imgur = 'https://api.imgur.com/3/upload';

	$scope.data.img;
	$scope.data.errorupload = false;

	var pics = [];

	$scope.pics = function() {
		return pics;
	}

	$scope.supprimer = function(src) {
		for (var i = 0; i < pics.length; i++) {
			if (pics[i] == src) {
				pics.splice(i);
			}
		}
	}

	$scope.data.upload = function() {

		var img = '';
		img = $scope.data.img;
		img = img.replace(/data:image\/gif;base64,/g, "").replace(/data:image\/png;base64,/g, "").replace(/data:image\/jpeg;base64,/g, "");

		$http({
			headers: {
				'Authorization': 'Client-ID ' + API_key
			},
			url: url_API_imgur,
			method: 'post',
			data: {
				image: img,
				type: 'base64'
			}
		}).then(function successCallback(response) {
			pics.push(response.data.data.link);
			insertPics(response.data.data.link);
		}, function errorCallback(err) {
			throw "error file upload";
			$scope.data.errorupload = true;
		});

	};
  
	startPage();
	
})

.controller("ProfileCtrl", function($scope, $routeParams, $location, data) {

  $scope.data = {};
  $scope.data.result = false;
  $scope.data.user = {};
  $scope.data.items = [];
  $scope.data.commentsOnUser = [];
  
  $scope.setUser = function(datareturn){
	  if (datareturn.length !== 0){
		  $scope.data.result = true;
		  $scope.data.user = datareturn;
		  data.get('item/getItemsByUser/' + datareturn.id, '', $scope.setCurrentItems);
		  data.get('comment/getByUser/'+datareturn.id,'',$scope.setCurrentComments);
	  }	 
  }
  
  $scope.setCurrentComments = function(data){
	  $scope.data.commentsOnUser = data;
	  for (var i = 0; i <  $scope.data.commentsOnUser.lenght; i++){
		  if ($scope.data.commentsOnUser[i].noting.id == $scope.data.user.id){
			  $scope.data.commentsOnUser[i].splice(i, 1);
		  }
	  }
  }

	$scope.setCurrentItems = function(data) {
		$scope.data.items = data;
	}
  
  $scope.data.userFinal = function() {
	  return $scope.data.user;
  };
  
  $scope.itemsfinal = function() {
		return $scope.data.items;
	};
	
	$scope.detail = function(itemId) {
		$location.path('/item/' + itemId);
	};
	
	$scope.userDetail = function(userId) {
		$location.path('/user/' + userId);
	};
  
  var startPage = function() {
	data.get('user/getById/' +$routeParams.userId, '', $scope.setUser);  
  };

  startPage();

});