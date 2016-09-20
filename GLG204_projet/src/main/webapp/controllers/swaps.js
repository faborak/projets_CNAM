'use strict';

angular.module('swapCatControllers', ['ngRoute','requeteur', 'business'])

.controller("SwapListCtrl", function($scope, $location, data, swapStatus) {

  $scope.data = {};
  $scope.data.search = "";
  $scope.data.currentuser = {};
  $scope.data.swaps = [];
  $scope.data.swapsfinal = [];

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

  $scope.setCurrentUser = function(datareturn) {
	$scope.data.currentuser = datareturn;
	data.get('deal/getByUser/'+ datareturn.id,'' , $scope.setCurrentDeals);
  }

  $scope.setCurrentDeals = function(data){
	$scope.data.swaps = data;
	for (var i=0; i < $scope.data.swaps.length; i++){
		$scope.data.swaps[i].libelleStatus = swapStatus.getLabelStatus($scope.data.currentuser.id, $scope.data.swaps[i]);
	}
	$scope.data.swapsfinal = $scope.data.swaps;
  }

  function filterSwaps(scope) {
    if ($scope.data.search != "") {
      $scope.data.swapsfinal = [];
      for (var i = 0; i < $scope.data.swaps.length; i++) {
        if ($scope.data.swaps[i].initiator.name.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].initiator.lastname.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].proposed.name.indexOf($scope.data.search) != -1 ||
          $scope.data.swaps[i].proposed.lastname.indexOf($scope.data.search) != -1) {
          $scope.data.swapsfinal.push($scope.data.swaps[i]);
        }
      }
    }
    else {
      $scope.data.swapsfinal = $scope.data.swaps;
    }
  };

  $scope.search = function() {
    return $scope.data.search;
  };

  $scope.swapsfinal = function() {
    return $scope.data.swapsfinal;
  };

  $scope.$watch($scope.search, filterSwaps);
  
  $scope.detail = function(swapId) {
      $location.path('/swaps/'+swapId)
  };
  
  $scope.showProfile = function(userId) {
      $location.path('/profile/'+userId)
  };
  
  startPage();

})


.controller("SwapDetailCtrl", function($scope, $routeParams, $location, $window, swapStatus, data) {

	  $scope.data = {};
	  $scope.data.currentuser = {};
	  $scope.data.otheruser = {};
	  $scope.data.swap = {};
	  $scope.data.currentitems = [];
	  $scope.data.currentDealItems = [];
	  $scope.data.otheritems = [];
	  $scope.data.otherDealItems = [];
	  $scope.data.total = 0;
	  $scope.data.LibelleTotal = '';
	  $scope.data.modify = false;

	  var startPage = function() {
			var params = {
				"token" : window.sessionStorage.getItem("token")
			};
			data.post('user/getCurrentUser', params, $scope.setCurrentUser);
		};

		$scope.setCurrentUser = function(datareturn) {
			if (datareturn == null){
				window.sessionStorage.setItem('token', '')
				$location.path=('/disconnectd');
			} else {
		     	$scope.data.currentuser = datareturn;
			   data.get('deal/get/'+ $routeParams.swapId,'' , setCurrentSwap);
			}
			data.get('item/getItemsByUser/'+$scope.data.currentuser.id,'',$scope.setCurrentItems);
		}
		
		$scope.setOtherItems = function(data){
			  $scope.data.otheritems = data;
			  for (var i = 0; i < $scope.data.otheritems.length; i++) {
				    for (var j = 0; j < $scope.data.otherDealItems.length; j++) {
				        if ($scope.data.otheritems[i].idSwapObjet == $scope.data.otherDealItems[j].idSwapObjet) {
				          $scope.data.otheritems.splice(i, 1);
				        } 
				    }    
			  }
		 }
		
		$scope.setCurrentItems = function(data){
			$scope.data.currentitems = data;
			for (var i = 0; i < $scope.data.currentitems.length; i++) {
				for (var j = 0; j < $scope.data.otherDealItems.length; j++) {
			        if ($scope.data.currentitems[i].idSwapObjet == $scope.data.currentDealItems[j].idSwapObjet) {
			          $scope.data.currentitems.splice(i, 1);
			        } 
				}    
		    }
		}
		
		var setCurrentSwap = function(datareturn){
			$scope.data.swap = datareturn;
			$scope.data.swap.libelleStatus = swapStatus.getLabelStatus($scope.data.currentuser.id, $scope.data.swap);
			if (datareturn.initiator.id == $scope.data.currentuser.id){
			  $scope.data.otheruser =	datareturn.proposed;
			} else {
			  $scope.data.otheruser = datareturn.initiator;
			}
			data.get('item/getItemsByUser/'+$scope.data.otheruser.id,'',$scope.setOtherItems);
			for (var i = 0; i < $scope.data.swap.swapObjects.length; i++){
				if ($scope.data.swap.swapObjects[i].owner.id == $scope.data.currentuser.id){
					$scope.data.currentDealItems.push($scope.data.swap.swapObjects[i]);
				} else {
					$scope.data.otherDealItems.push($scope.data.swap.swapObjects[i]);
				}
			}
			computeTotal();
		}
		
	var computeTotal = function(){
		$scope.data.total = 0;
		for (var i = 0; i < $scope.data.otherDealItems.length; i++) {
		     $scope.data.total -=  $scope.data.otherDealItems[i].cost;
		}
		for (var i = 0; i < $scope.data.currentDealItems.length; i++) {
			$scope.data.total +=  $scope.data.currentDealItems[i].cost;
		}
		if ($scope.data.total > 0){
			$scope.data.LibelleTotal = 'votre interlocuteur vous devra ' + $scope.data.total + ' euros.';
		} else if ($scope.data.total < 0){
			$scope.data.LibelleTotal = 'Vous devrez à la personne ' + Math.abs($scope.data.total) + ' euros.';
		} else {
			$scope.data.LibelleTotal = "Vous ne perdrez ni ne gagnerez d''argent dans l''échange.x";
		}
	}
	
	$scope.sendMail = function(emailId,subject,message){
	    $window.open("mailto:"+ emailId + "?subject=" + subject+"&body="+message,"_self");
	};
	
	$scope.itemDetail = function(itemId){
	  $location.path('item/'+itemId);
	}
	  
	$scope.userDetail = function(userId){
		  $location.path('profile/'+userId);
	}
	
	$scope.modify = function(){
		  $scope.data.modify = true;
	}
	
	$scope.accept = function(){
		var status = '';
		if ($scope.data.swap.initiator.id == $scope.data.currentuser.id){
			status = "Transaction acceptée par l'initiateur";
		} else {
			status = 'Transaction acceptée par le proposed';
		}
		var params={
				'id': $scope.data.swap.idDeal,
				'status': status
		}
		data.postAuthorize('deal/modifyStatus/', params, $scope.refresh);
	}
	
	$scope.refuse = function(){
		var status = '';
		if ($scope.data.swap.initiator.id == $scope.data.currentuser.id){
			status = "Transaction refusée par l'initiateur";
		} else {
			status = 'Transaction refusée par le proposed';
		}
		var params={
				'id': $scope.data.swap.idDeal,
				'status': status
		}
		data.postAuthorize('deal/modifyStatus/', params, $scope.refresh);
	}
	
	$scope.validate = function(){
		var status = '';
		if ($scope.data.swap.initiator.id == $scope.data.currentuser.id){
			status = "Transaction validée";
		} else {
			status = 'Transaction validée';
		}
		var params={
				'id': $scope.data.swap.idDeal,
				'status': status
		}
		data.postAuthorize('deal/modifyStatus/', params, $scope.refresh);
	}
	
	$scope.addToDeal = function(idItem) {
		  
		  for (var i = 0; i < $scope.data.otheritems.length; i++) {
		        if ($scope.data.otheritems[i].idSwapObjet == idItem) {
		          $scope.data.otherDealItems.push($scope.data.otheritems[i]);
		          $scope.data.otheritems.splice(i, 1);
		        } 
		  }
		  for (var i = 0; i < $scope.data.currentitems.length; i++) {
		        if ($scope.data.currentitems[i].idSwapObjet == idItem) {
		          $scope.data.currentDealItems.push($scope.data.currentitems[i]);
		          $scope.data.currentitems.splice(i, 1);
		        } 
		  }
		  
		  computeTotal();
	  };
	  
	  $scope.removeFromDeal = function(idItem) {
		  
		  for (var i = 0; i < $scope.data.otherDealItems.length; i++) {
		        if ($scope.data.otherDealItems[i].idSwapObjet == idItem) {
		          $scope.data.otheritems.push($scope.data.otherDealItems[i]);
		          $scope.data.otherDealItems.splice(i, 1);
		        } 
		  }
		  for (var i = 0; i < $scope.data.currentDealItems.length; i++) {
		        if ($scope.data.currentDealItems[i].idSwapObjet == idItem) {
		          $scope.data.currentitems.push($scope.data.currentDealItems[i]);
		          $scope.data.currentDealItems.splice(i, 1);
		        } 
		  }
		  
		  computeTotal();
		  
	  }
	  
	$scope.data.otherDealItemsFinal = function() {
		return $scope.data.otherDealItems;
	};
	
	$scope.data.otherItemsFinal = function() {
		return $scope.data.otheritems;
	};
	  
	$scope.data.currentDealItemsFinal = function() {
		return $scope.data.currentDealItems;
	};
	
	$scope.data.currentItemsFinal = function() {
		return $scope.data.currentitems;
	};
	
	$scope.refresh = function() {
		  $location.path('/swap'+$scope.data.swap.idDeal)
		}
	
	$scope.retour = function() {
	  $location.path('/swaps')
	}
  
  startPage();
  
});