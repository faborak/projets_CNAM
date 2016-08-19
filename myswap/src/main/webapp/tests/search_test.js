'use strict';

describe('myApp.home module', function() {

  beforeEach(module('myApp.followswap'));

  describe('home controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var homeCtrl = $controller('HomeCtrl');
      expect(homeCtrl).toBeDefined();
    }));

  });
});