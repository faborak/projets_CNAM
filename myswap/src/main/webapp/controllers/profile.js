'use strict';

angular.module('profileControllers', ['ngRoute'])

.controller("SelfProfileCtrl", function($scope, $http, $location) {

  $scope.data = {};
  $scope.data.user = {};
  $scope.data.user.final = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
      $scope.data.user.final = resultat;
    });
  };

  $scope.updateUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/updateUser',
      params: {
        "id": $scope.data.user.id,
        "name": $scope.data.user.final.name,
        "lastname": $scope.data.user.final.lastname,
        "email": $scope.data.user.final.account.email,
        "emailChecked": $scope.data.user.final.account.emailChecked,
        "phoneNumber": $scope.data.user.final.account.phoneNumber,
        "phoneChecked": $scope.data.user.final.account.phoneChecked,
        "street": $scope.data.user.final.adress.street,
        "state": $scope.data.user.final.adress.state,
        "zipcode": $scope.data.user.final.adress.zipcode,
        "city": $scope.data.user.final.adress.city,
        "pics": $scope.data.user.final.pics,
        "school": $scope.data.user.final.infos.school,
        "job": $scope.data.user.final.infos.job,
        "about": $scope.data.user.final.infos.about,
      }
    }).success(function(resultat) {
      //$scope.resultat = resultat;
    });
  };



  function checkphone(scope) {
    if ($scope.data.user.final.account.phoneNumber != $scope.data.user.account.phoneNumber) {
      $scope.data.user.final.account.phoneChecked = false;
    }
  };

  function checkmail(scope) {
    if ($scope.data.user.final.account.email != $scope.data.user.account.email) {
      $scope.data.user.final.account.emailChecked = false;
    }
  };

  /* lancement de la recherche au départ de l'application */
  /*getCurrentUser();

  /* Mock  */
  $scope.data.user = {
    "id": 1,
    "name": "Jean-Philippe",
    "lastname": "Larseur",
    "pic": null,
    "account": {
      "phoneNumber": "0640223315",
      "email": "test1@gmail.com",
      "emailChecked": true,
      "phoneChecked": true
    },
    "adress": {
      "street": "9 place des mystères",
      "zipcode": "77184",
      "city": "Courbevoie",
      "state": "Ile-de-France",
      "country": "Emerainville"
    },
    "commentsOnUser": [{
      "id": 1,
      "label": "Bonne réception, réactif et agréable à l'écrit. Très bien !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Réceptionné ce matin ! Top !",
      "mark": 4
    }],
    "commentsWrited": [{
      "id": 5,
      "label": "Toujours super !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Bien, mais un petit retard à signaler dans la livraison.",
      "mark": 3
    }]
  }

  $scope.data.user.final = $scope.data.user;

  $scope.$watch($scope.data.user.final.account.phoneNumber, checkphone);
  $scope.$watch($scope.data.user.final.account.mail, checkmail);

})


.controller("CommentsCtrl", function($scope, $http) {

  $scope.data = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
    });
  };

  $scope.comments = function() {
    return $scope.data.user.commentsOnUser;
  };

  // a enelever pour democker
  /*getCurrentUser();*/

  $scope.data.user = {
    "id": 1,
    "name": "Jean-Philippe",
    "lastname": "Larseur",
    "pic": null,
    "account": {
      "phoneNumber": "0640223315",
      "email": "test1@gmail.com",
      "emailChecked": true,
      "phoneChecked": true
    },
    "adress": {
      "street": "9 place des mystères",
      "zipcode": "77184",
      "city": "Courbevoie",
      "state": "Ile-de-France",
      "country": "Emerainville"
    },
    "commentsOnUser": [{
      "id": 1,
      "label": "Bonne réception, réactif et agréable à l'écrit. Très bien !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Réceptionné ce matin ! Top !",
      "mark": 4
    }],
    "commentsWrited": [{
      "id": 5,
      "label": "Toujours super !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Bien, mais un petit retard à signaler dans la livraison.",
      "mark": 3
    }]
  }



})

.controller("PicsCtrl", function($scope, $http) {

  $scope.data = {};

  var getCurrentUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/getCurrentUser'
    }).success(function(resultat) {
      $scope.data.user = resultat;
    });
  };

  $scope.comments = function() {
    return $scope.data.user.commentsOnUser;
  };

  $scope.ajouter = function() {
    var fichier = document.getElementById('fichier').files[0];
    var lecture = new FileReader();
    lecture.onloadend = function(evenement) {
      var donnees = evenement.target.result;
      //Traitez ici vos données binaires. Vous pouvez par exemple les envoyer à un autre niveau du framework avec $http ou $ressource
    }
    lecture.readAsBinaryString(fichier);
  }

  $scope.pics = function() {
    return $scope.data.user.pics;
  };

  $scope.supprimer = function(src) {
    for (var i = 0; i < $scope.data.user.pics.length; i += 1) {
      if ($scope.data.user.pic[i] === src) {
        $scope.data.user.pic[i].delete;
        $scope.updateUser($scope.data.user);
      }
    }
  };

  $scope.updateUser = function() {
    $http({
      method: 'post',
      url: 'http://92.90.70.23:12345/myswap/rest/user/updateUser',
      params: {
        "id": $scope.data.user.id,
        "name": $scope.data.user.final.name,
        "lastname": $scope.data.user.final.lastname,
        "email": $scope.data.user.final.account.email,
        "emailChecked": $scope.data.user.final.account.emailChecked,
        "phoneNumber": $scope.data.user.final.account.phoneNumber,
        "phoneChecked": $scope.data.user.final.account.phoneChecked,
        "street": $scope.data.user.final.adress.street,
        "state": $scope.data.user.final.adress.state,
        "zipcode": $scope.data.user.final.adress.zipcode,
        "city": $scope.data.user.final.adress.city,
        "pics": $scope.data.user.final.pics,
        "school": $scope.data.user.final.infos.school,
        "job": $scope.data.user.final.infos.job,
        "about": $scope.data.user.final.infos.about,
      }
    }).success(function(resultat) {
      //$scope.resultat = resultat;
    });
  };

  $scope.data.user = {
    "id": 1,
    "name": "Jean-Philippe",
    "lastname": "Larseur",
    "pics": [],
    "account": {
      "phoneNumber": "0640223315",
      "email": "test1@gmail.com",
      "emailChecked": true,
      "phoneChecked": true
    },
    "adress": {
      "street": "9 place des mystères",
      "zipcode": "77184",
      "city": "Courbevoie",
      "state": "Ile-de-France",
      "country": "Emerainville"
    },
    "commentsOnUser": [{
      "id": 1,
      "label": "Bonne réception, réactif et agréable à l'écrit. Très bien !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Réceptionné ce matin ! Top !",
      "mark": 4
    }],
    "commentsWrited": [{
      "id": 5,
      "label": "Toujours super !",
      "mark": 5
    }, {
      "id": 2,
      "label": "Bien, mais un petit retard à signaler dans la livraison.",
      "mark": 3
    }]
  }



});