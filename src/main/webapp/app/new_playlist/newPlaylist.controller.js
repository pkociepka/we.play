/**
 * Created by Roksana on 2016-04-17.
 */
(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .controller('NewPlaylistController', NewPlaylistController);

    NewPlaylistController.$inject = ['$scope'];
    function NewPlaylistController  ($scope) {
        $scope.data = {
            step: 1
        };

        $scope.nextStep = function() {
            $scope.data.step += 1;
        }
    }
})();
