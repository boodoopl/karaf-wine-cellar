var winecellarServices = angular.module('winecellarServices', ['ngResource']);

winecellarServices.factory('Wine', ['$resource',
    function($resource){
        return $resource('http://localhost:8181/cxf/wines/:id', {}, {
            query: {method:'GET', isArray:true}
        });
    }]);