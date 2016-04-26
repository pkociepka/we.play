(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .controller('PlaylistController', PlaylistController);

    PlaylistController.$inject = ['$scope'];
    function PlaylistController  ($scope) {
        $scope.tabs = [
            { title:'Dynamic Title 1', content:'Dynamic content 1' },
            { title:'Dynamic Title 2', content:'Dynamic content 2' }
        ];

        $scope.model = {
            name: 'Tabs'
        };
    }
})();
