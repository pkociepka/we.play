(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('NewPlaylistController', NewPlaylistController);


    NewPlaylistController.$inject = ['PreferencesFactory'];

    function NewPlaylistController(PreferencesFactory) {
        var vm = this;
        vm.step = 1;
        vm.newFriend = null;
        vm.newSong = null;

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
        vm.searchSong = PreferencesFactory.addNewSong;

        vm.generate = generate;

        vm.nextStep = function () {
            vm.step += 1;
        };

        vm.prevStep = function () {
            vm.step -= 1;
        };

        function generate() {
            PreferencesFactory.sendPreferences(vm.params);
        }

    }
})();