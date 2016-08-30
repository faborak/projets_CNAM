angular.module('requeteur', [])

.service('data', ['$http', function($http, $location) {
    "use strict";

    this.get = function(url, params, callback) {
        $http({
            method: 'GET',
            url: 'http://www.localhost:8080/myswap/rest/' + url,
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
    	var baseUrl = "http://www.localhost:8080/myswap/rest/";	
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
    	 var baseUrl = "http://www.localhost:8080/myswap/rest/";	
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
   	 var baseUrl = "http://www.localhost:8080/myswap/rest/";	
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

}]);