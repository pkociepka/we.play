/**
 * Created by P on 16.11.2016.
 */
(function() {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('account', {
            abstract: true,
            parent: 'app'
        });
    }
})();
