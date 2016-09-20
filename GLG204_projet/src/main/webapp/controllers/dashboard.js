'use strict';

angular.module('dashboardCatControllers', ['ngRoute', 'requeteur', 'business'])

.controller("DashboardCtrl", function($scope, $location, data, swapStatus, $window) {

  $scope.data = {};
  $scope.data.currentuser = {};
  $scope.data.mailchecked = {};
  $scope.data.phonechecked = {};
  $scope.data.deals = [];
  $scope.data.news = 0;
  $scope.data.mailchecked = "Email vérifié";
  $scope.data.phonechecked = "Téléphone vérifié";
  
  var startPage = function() {
		var params = {
			"token" : window.sessionStorage.getItem("token")
		};
		data.post('user/getCurrentUser', params, $scope.setCurrentUser);
		data.post('authentication/islogged', params, $scope.setLogged);
   };
   
   $scope.setLogged = function(data) {
		if (data.isLogged == false){
			window.sessionStorage.setItem("token", "");
			$location.path('/disconnected');
		}
   }
   
   $scope.setCurrentUser = function(datareturn) {
		$scope.data.currentuser = datareturn;
		if ($scope.data.currentuser.account.emailChecked){
	        $scope.data.mailchecked = "Email vérifié";
	      } else {
	        $scope.data.mailchecked = "envoyer un email de vérfication";
	      }
	      if ($scope.data.currentuser.account.phoneChecked){
	        $scope.data.phonechecked = "Telephone vérifié";
	      } else {
	        $scope.data.phonechecked = "envoyer un SMS de vérfication";
	      }
	      data.get('deal/getByUser/'+$scope.data.currentuser.id, '', $scope.setCurrentDeals);
	}
   
   $scope.setCurrentDeals = function(data) {
	   $scope.data.deals = data;
	   for (var i = 0; i < $scope.data.deals.length; i++){
		   $scope.data.deals[i].libelleStatus = swapStatus.getLabelStatus($scope.data.currentuser.id, $scope.data.deals[i]);
	        if ( $scope.data.deals[i].dateModification > $scope.data.currentuser.lastlogin){
	          $scope.data.news++;
	        }
	        if ( $scope.data.deals[i].initiator.id != $scope.data.currentuser.id){
	           $scope.data.deals[i].othername = $scope.data.deals[i].initiator.name;
	        } else {
	          $scope.data.deals[i].othername = $scope.data.deals[i].proposed.name;
	        }
	          
	      }
   }
   
   $scope.sendMail = function(){
	    $window.open("mailto:"+ $scope.data.currentuser.account.email + "?subject=verification&body=lien","_self");
	}; 

  $scope.detailDeal = function(dealId) {
    $location.path('/swaps/' + dealId)
  };

  $scope.detailUser = function(userId) {
    $location.path('/user/' + userId)
  };
 
 startPage(); 

});