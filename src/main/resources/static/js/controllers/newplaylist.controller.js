(function() {
    'use strict';

    angular
        .module('weplay')
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
        
        vm.searchFriend = function () {
            if (vm.data.newFriend != null) {
                vm.data.friends.push(vm.data.newFriend);
                vm.data.newFriend = null;
            }
        };
        
        vm.searchSong = function () {
            if (vm.data.newSong != null) {
                vm.data.songs.push(vm.data.newSong);
                vm.data.newSong = null;
            }
        }
    }
})();