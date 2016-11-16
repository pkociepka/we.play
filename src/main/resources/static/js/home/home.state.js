/**
 * Created by P on 16.11.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'js/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        })
    }
})();