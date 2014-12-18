var winecellarServices = angular.module('winecellarServices', ['ngResource']);

winecellarServices.factory('Wine', ['$resource',
    function($resource){
        return $resource('../cxf/wines/:id', {}, {
            query: {method:'GET', isArray:true},
            update: {method:'PUT'},
            delete: {method:'DELETE'}
        });
    }]);