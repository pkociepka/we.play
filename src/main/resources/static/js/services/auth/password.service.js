/**
 * Created by P on 25.10.2016.
 */
(function() {
    'use strict';

    angular
        .module('weplay')
        .factory('Password', Password);

    Password.$inject = ['$resource'];

    function Password($resource) {
        return $resource('api/account/change_password', {}, {});
    }
})();