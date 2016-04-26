(function() {
    'use strict';

    angular
        .module('wePlayApp')
        .controller('NewPlaylistController', NewPlaylistController);

    NewPlaylistController.$inject = ['$scope', '$http'];
    function NewPlaylistController  ($scope, $http) {
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
            $http.get('some/'+$scope.data.newSong).success(function(response) {
                $scope.data.songs = response;
                $scope.data.newSong = null;
            }).error(function () {
                console.log('error');
            })
        }
    }
})();
