(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('NewPlaylistController', NewPlaylistController);


    NewPlaylistController.$inject = ['PreferencesFactory', '$http'];

    function NewPlaylistController(PreferencesFactory, $http) {
        var vm = this;
        vm.step = 1;
        vm.newFriend = null;
        vm.newSong = null;
        vm.songs = [];

        //step 1
        vm.getFriends = PreferencesFactory.getFriends;
        vm.searchFriend = PreferencesFactory.addNewFriend;

        //step 2
        vm.params = {
            energy: 50,
            hot: 50,
            dance: 50,
            mood: 50
        };

        //step 3
        vm.setActivity = PreferencesFactory.setActivity;

        //step 4
        //vm.searchSong = PreferencesFactory.addNewSong;

        vm.generate = generate;

        vm.nextStep = function () {
            vm.step += 1;
        };

        vm.prevStep = function () {
            vm.step -= 1;
        };

        vm.searchSong = function (val) {
            return $http.get('tracks/' + val).then(function (response) {
                return response.data;
            })
        };

        vm.addSong = function (val) {
            vm.songs.push(val);
        };

        function generate() {
            PreferencesFactory.sendPreferences(vm.params);
        }
    }
})();