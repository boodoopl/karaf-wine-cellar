'use strict';

/* Controllers */

var winecellarControllers = angular.module('winecellarControllers', []);

winecellarControllers.controller('EventsCtrl', ['$scope', 'WineNotification',
    function($scope, WineNotification) {
        $scope.WineNotification = WineNotification;
    }]);

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

        if($routeParams.id == "new" || $routeParams.id == "" || $routeParams.id == 0) {
            $scope.editedWine = new Wine();

            $scope.editedWine.id = 0;
            $scope.editedWine.name = '';
            $scope.editedWine.grapes = '';
            $scope.editedWine.country = '';
            $scope.editedWine.region = '';
            $scope.editedWine.year = '';
            $scope.editedWine.description = '';
            $scope.editedWine.imageId = 0;

            $scope.isEditing = true;
            $scope.wine = angular.copy($scope.editedWine);
            $scope.isNew = true;
        }
        else {
            $scope.wine = Wine.get({id: $routeParams.id});
            $scope.isEditing = false;
        }

        $scope.toggleEdit = function() {

            if ($scope.isNew) {
                window.location = "#/wines";
            }
            else {
                $scope.isEditing = !$scope.isEditing;

                if ($scope.isEditing) {
                    $scope.editedWine = angular.copy($scope.wine);
                }
            }
        };

        $scope.saveWine = function () {
            if ($scope.editedWine.id > 0) {
                $scope.editedWine.$update({id: $routeParams.id});
                window.location.reload();
            }
            else {
                $scope.editedWine.$save();
                window.location = "#/wines";
            }
        };

        $scope.deleteWine = function () {
            $scope.wine.$delete({id: $routeParams.id});
            window.location = "#/wines";
        }
    }]);
