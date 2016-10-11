(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .controller('NewPlaylistController', NewPlaylistController);

    // NewPlaylistController.$inject = ['$scope'];
    function NewPlaylistController  () {
        var vm = this;

        vm.data = {
            step: 1,
            friends: [],
            newFriend: null,
            songs: [],
            newSong: null
        };

        vm.nextStep = function() {
            vm.data.step += 1;
        };

        vm.prevStep = function () {
            vm.data.step -= 1;
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