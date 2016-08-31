angular.module('requeteur', [])

.service('data', ['$http', function($http, $location) {
    "use strict";
    var baseUrl = "http://www.localhost:8080/myswap/rest/";	
    
    this.get = function(url, params, callback) {
        $http({
            method: 'GET',
            url: baseUrl + url,
            params : params
        }).
        success(function(data, status, headers, config) {
        	callback(data);
        }).
        error(function(data, status, headers, config) {
        	throw "Probleme dans l'appel à " + url + ", status : " + status;
        });
    };
    
    this.getAuthorized = function(url, params, callback) {
        $http({
            method: 'GET',
            url: baseUrl + url,
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/x-www-form-urlencoded',
                'authorization': localStorage.getItem("token")
            },
            params : params
        }).
        success(function(data, status, headers, config) {
            callback(data);
        }).
        error(function(data, status, headers, config) {
        	throw "Probleme dans l'appel à " + url + ", status : " + status;
        	if(status == '401'){
        		$location.path('/disconnected') ;
        	}
        });
    };

    this.post = function(url, params, callback) {
        $http({
            method: 'POST',
            url: baseUrl + url,
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/x-www-form-urlencoded',
                'Authorization': 'Bearer '+ window.sessionStorage.getItem("token")
            },
            transformRequest:function(obj) {
	            var str = [];
	            for(var p in obj)
	            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	            return str.join("&");}, 
            params: params,
        }).
        success(function(data, status, headers, config) {
            callback(data);
        }).
        error(function(data, status, headers, config) {

        	throw "Probleme dans l'appel à " + url + ", status : " + status;
        });
    };
    
    this.postAuthorize = function(url, params, callback) {
       $http({
           method: 'POST',
           url: baseUrl + url,
           headers: {
               'Accept': 'application/json',
               'Content-type': 'application/x-www-form-urlencoded',
               'authorization': localStorage.getItem("token")
           },
           transformRequest:function(obj) {
	            var str = [];
	            for(var p in obj)
	            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	            return str.join("&");}, 
           params: params,
       }).
       success(function(data, status, headers, config) {
           callback(data);
       }).
       error(function(data, status, headers, config) {
	       	throw "Probleme dans l'appel à " + url + ", status : " + status;
	       	if(status == '401'){
	    		$location.path('/disconnected') ;
	    	}
       });
   };
   
   this.getCurrentUser = function(callback) {
	   var params = {
				"token" : window.sessionStorage.getItem("token")
		};   
	   
       $http({
           method: 'GET',
           url: baseUrl + 'user/getCurrentUser',
           params : params
       }).
       success(function(data, status, headers, config) {
       	callback(data);
       }).
       error(function(data, status, headers, config) {
    	   if(status == '401'){
	    		$location.path('/disconnected') ;
	    	}
       });
   };

}]);