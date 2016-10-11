(function () {
    'use strict';

    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$routeProvider', '$httpProvider'];

    function stateConfig($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'home',
            controllerAs: 'vm'
        // }).when('/login', {
        //     templateUrl: 'login.html',
        //     controller: 'navigation',
        //     controllerAs: 'vm'
        }).when('/new', {
            templateUrl: 'new_playlist.html',
            controller: 'NewPlaylistController',
            controllerAs: 'vm'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }
})();