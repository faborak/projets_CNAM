'use strict';

describe('dashboardCatControllers module', function() {

  // load the controller's module
  beforeEach(module('dashboardCatControllers'));

  // Initialize the controller and a mock scope
  beforeEach(inject(function($controller, $rootScope) {

    var MainCtrl,
      scope;

    scope = $rootScope.$new();
    MainCtrl = $controller('DashboardCtrl', {
      $scope: scope
    });
  }));

  // a describe per controller of the module
  describe('DashboardCtrl controller', function() {



    it('should have Ctrl to be described', function() {
      //spec body
      //var dashboardCtrl = $controller('DashboardCtrl');
      expect(MainCtrl).toBeDefined();
    });

    it("contains a function to call Current User", function() {
      expect(scope.getCurrentUser).toBeDefined;
    });

    it("contains a function to see detail User", function() {
      expect(scope.detailUser).toBeDefined;
    });

  });

});