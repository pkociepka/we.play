/**
 * Created by P on 18.11.2016.
 */
(function() {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', 'SpotifyProvider'];

    function stateConfig($stateProvider, SpotifyProvider) {
        $stateProvider.state('spotify_login', {
            parent: 'app',
            url: '/spotify_login',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'js/spotify/spotify.html',
                    controller: 'SpotifyController',
                    controllerAs: 'vm'
                }
            }
        });

        SpotifyProvider.setClientId('1f04ae7d7737482eb47594961c217f15');
        SpotifyProvider.setRedirectUri('http://localhost:8080/spotify_callback.html');
        SpotifyProvider.setScope('user-read-private playlist-read-private playlist-modify-private playlist-modify-public');
    }
})();