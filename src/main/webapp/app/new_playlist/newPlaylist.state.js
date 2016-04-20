(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('new_playlist', {
            parent: 'app',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/new_playlist/new_playlist.html',
                    controller: 'NewPlaylistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('newPlaylist');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
