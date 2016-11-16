(function() {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('newplaylist', {
            parent: 'app',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'js/newplaylist/new_playlist.html',
                    controller: 'NewPlaylistController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();