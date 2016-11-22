(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('NewPlaylistController', NewPlaylistController);


    NewPlaylistController.$inject = ['PreferencesFactory', 'Spotify'];

    function NewPlaylistController(PreferencesFactory, Spotify) {
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
            return Spotify.search(val, 'track').then(function (data) {
                var list = [];
                var items = data.tracks.items;
                for (var i = 0; i < items.length; i++) {
                    var artists = "";
                    artists += items[i].artists[0].name;
                    for (var j = 1; j < items[i].artists.length; j++) {
                        artists += ", " + items[i].artists[j].name;
                    }
                    list.push({
                        id : items[i].id,
                        uri : items[i].uri,
                        artists : artists,
                        name : items[i].name,
                        album : items[i].album.name
                    });
                }
                return list;
            })
        };

        vm.addSong = function () {
            if (vm.songs.indexOf(vm.newSong) != -1) {
                return;
            }
            vm.songs.push(vm.newSong);
            vm.songs.sort(function (a, b) {
                var left = a.artists.toLowerCase();
                var right = b.artists.toLowerCase();
                return left > right ? 1 : left < right ? -1 : 0;
            })
        };

        vm.deleteSong = function (index) {
            vm.songs.splice(index, 1);
        };

        vm.onSelect = function ($item, $model, $label) {
            vm.item = $item;
            vm.model = $model;
            vm.label = $label;
            vm.newSong = $item;
        };

        function generate() {
            PreferencesFactory.sendPreferences(vm.params);
        }
    }
})();