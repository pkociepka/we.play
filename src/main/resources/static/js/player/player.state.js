/**
 * Created by Kurtzz on 2016-12-14.
 */
(function() {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('player', {
            parent: 'app',
            url: '/player',
            data: {
                authorities: ['ROLE_USER']
            },
            params: {
                tracks: ''
            },
            views: {
                'content@': {
                    templateUrl: 'js/player/player.html',
                    controller: 'PlayerController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();