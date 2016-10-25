/**
 * Created by P on 25.10.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .factory('Sessions', Sessions);

    Sessions.$inject = ['$resource'];


    function Sessions($resource) {
        return $resource('api/account/sessions/:series', {}, {
            'getAll': { method: 'GET', isArray: true}
        });
    }
})
();
