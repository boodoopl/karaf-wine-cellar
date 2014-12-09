'use strict';

/* App Module */

var winecellarApp = angular.module('winecellarApp', [
    'ngRoute',
    'winecellarControllers',
    'winecellarServices'
]);

winecellarApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/wines', {
                templateUrl: 'partials/wine-list.html',
                controller: 'WineListCtrl'
            }).
            when('/wines/:id', {
                templateUrl: 'partials/wine-details.html',
                controller: 'WineDetailsCtrl'
            }).
            otherwise({
                redirectTo: '/wines'
            });
    }]);
