'use strict';

describe('dashboardCatControllers module', function() {

  beforeEach(module('DashboardCtrl'));

  describe('home controller', function(){

    it('should have the Ctrl to be defined', inject(function($controller) {
      //spec body
      var homeCtrl = $controller('HomeCtrl');
      expect(homeCtrl).toBeDefined();
    }));
    
  });
});