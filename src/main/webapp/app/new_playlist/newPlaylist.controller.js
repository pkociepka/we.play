(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .controller('NewPlaylistController', NewPlaylistController);

    NewPlaylistController.$inject = ['$scope'];
    function NewPlaylistController  ($scope) {
        $scope.data = {
            step: 1,
            friends: [],
            newFriend: null,
            songs: [],
            newSong: null
        };

        $scope.nextStep = function() {
            $scope.data.step += 1;
        };

        $scope.prevStep = function () {
            $scope.data.step -= 1;
        };

        $scope.searchFriend = function () {
            if ($scope.data.newFriend != null) {
                $scope.data.friends.push($scope.data.newFriend);
                $scope.data.newFriend = null;
            }
        };

        $scope.searchSong = function () {
            if ($scope.data.newSong != null) {
                $scope.data.songs.push($scope.data.newSong);
                $scope.data.newSong = null;
            }
        }
    }
})();
