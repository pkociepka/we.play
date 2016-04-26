
(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('playlist', {
            parent: 'app',
            url: '/playlist',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/playlist/playlist.html',
                    controller: 'PlaylistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('playlist');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
