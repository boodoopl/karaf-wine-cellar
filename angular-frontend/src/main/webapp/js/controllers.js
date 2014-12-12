'use strict';

/* Controllers */

var winecellarControllers = angular.module('winecellarControllers', []);

winecellarControllers.controller('WineListCtrl', ['$scope', 'Wine',
    function($scope, Wine) {

        $scope.winesLinear = Wine.query();

        $scope.winesLinear.$promise.then(function (result) {
            var colNum = 3;
            var index;
            $scope.winesMulti = [];

            for(var i = 0; i < $scope.winesLinear.length; i+=3) {
                var row = {columns: []};
                for(var j = 0; j < colNum; j++) {
                    index = i + j;
                    if (index < $scope.winesLinear.length) {
                        row.columns.push($scope.winesLinear[index]);
                    }
                }
                $scope.winesMulti.push(row);
            }
        });

        $scope.orderProp = 'name';
    }]);

winecellarControllers.controller('WineDetailsCtrl', ['$scope', '$routeParams', 'Wine',
    function($scope, $routeParams, Wine) {
        $scope.wine = Wine.get({id: $routeParams.id}, function(wine) {
            $scope.mainImageUrl = wine.images[0];
        });
    }]);