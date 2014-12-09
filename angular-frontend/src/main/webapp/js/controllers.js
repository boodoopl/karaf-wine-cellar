'use strict';

/* Controllers */

var winecellarControllers = angular.module('winecellarControllers', []);

winecellarControllers.controller('WineListCtrl', ['$scope', 'Wine',
    function($scope, Wine) {
        $scope.wines = Wine.query();
        $scope.orderProp = 'name';
    }]);

winecellarControllers.controller('WineDetailsCtrl', ['$scope', '$routeParams', 'Wine',
    function($scope, $routeParams, Wine) {
        $scope.wine = Wine.get({id: $routeParams.id}, function(wine) {
            $scope.mainImageUrl = wine.images[0];
        });
    }]);