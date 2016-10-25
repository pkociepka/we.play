/**
 * Created by P on 25.10.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .factory('Reqister', Register);

    Register.$inject = ['$resource'];

    function Register($resource) {
        return $resource('api/register', {}, {});
    }
});