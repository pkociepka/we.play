/**
 * Created by P on 18.11.2016.
 */
(function () {
    angular
        .module('weplay')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('callback', {
            parent: 'app',
            url: '/callback?access_token&token_type&expires_in',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'js/spotify/callback.html',
                    controller: 'CallbackController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                'urlFix': ['$location', function($location){
                    $location.url($location.url().replace("#","?"));
                }]
            }
        });
    }
})();