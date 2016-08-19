angular.module('requeteur', [])

.service('requeteur', ['$http', function($http) {

    "use strict"

    var baseUrl = "https://92.90.70.23:12345/myswap/rest/";

    this.get = function(url, callback) {
        $http({
            method: 'GET',
            url: baseUrl + url
        }).
        success(function(data, status, headers, config) {
            callback(data);
        }).
        error(function(data, status, headers, config) {
            throw "No data returned from " + url;
        });
    };

    this.post = function(url, callback, data) {
        $http({
            method: 'POST',
            url: baseUrl + url,
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/x-www-form-urlencoded',
                'authorization': localStorage.getItem("token")
            },
            params: data,
            paramSerializer: '$httpParamSerializerJQLike'
        }).
        success(function(data, status, headers, config) {
            callback(data);
        }).
        error(function(data, status, headers, config) {

            throw "No data returned from " + url;
        });
    };

}]);
