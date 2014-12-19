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

    }]);

winecellarControllers.controller('WineDetailsCtrl', ['$scope', '$routeParams', 'Wine',
    function($scope, $routeParams, Wine) {
        $scope.wine = Wine.get({id: $routeParams.id});

        $scope.isEditing = false;

        $scope.toggleEdit = function() {
            $scope.isEditing = !$scope.isEditing;

            if ($scope.isEditing) {
                $scope.editedWine = angular.copy($scope.wine);
            }
        };

        $scope.saveWine = function () {
            if ($scope.wine.id > 0)
                $scope.editedWine.$update({id: $routeParams.id});
            else
                $scope.wine.$save();
        };

        $scope.deleteWine = function () {
            $scope.wine.$delete({id: $routeParams.id});
        }
    }]);