'use strict';

describe('searchControllers module', function() {

  // load the controller's module
  beforeEach(module('searchControllers'));

  // a describe per controller of the module
  describe('GeneralSearchCtrl controller', function() {

    var GeneralSearchCtrl,
      scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function($controller, $rootScope) {
      scope = $rootScope.$new();
      GeneralSearchCtrl = $controller('GeneralSearchCtrl', {
        $scope: scope
      });
    }));

    it('should have Ctrl to boot', function() {
      expect(scope.startPage).toBeDefined();
    });

    it("contains a function to set categories", function() {
      expect(scope.setCategories).toBeDefined;
    });

    it("contains a function to set Tendances", function() {
      expect(scope.setTendances).toBeDefined;
    });

    it("contains a function to search", function() {
      expect(scope.rechercher).toBeDefined;
    });

  });

});