angular.module('postImgur', [])

.service('postImgur', ['$http', function($http) {

    "use strict";

    this.postPicToImgur = function(url, pic) {
        $http({
            method: 'POST',
            url: 'https://api.imgur.com/3/image',
            headers: {
                'Authorization': 'Client-ID 81f578c29a5d6fb'
            },
            data: {
                //'image': 'helloworld.jpg'
               'image' : pic
            },
            success: function() {
                console.log('cool');
            }
        });

    };

}]);
