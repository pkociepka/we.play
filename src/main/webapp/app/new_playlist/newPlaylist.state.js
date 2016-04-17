/**
 * Created by Roksana on 2016-04-17.
 */
(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('new_playlist', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
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
