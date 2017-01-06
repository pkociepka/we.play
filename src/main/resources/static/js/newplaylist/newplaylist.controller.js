(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('NewPlaylistController', NewPlaylistController);


    NewPlaylistController.$inject = ['$http', 'Spotify', '$state'];

    function NewPlaylistController($http, Spotify, $state) {
        var vm = this;
        vm.step = 1;
        vm.newUser = null;
        vm.users = [];
        vm.newTrack = null;
        vm.tracks = [];

        vm.sliderOptions = {
            showSelectionBar: true,
            hideLimitLabels: true,
            floor: 0,
            ceil: 1,
            step: 0.01,
            precision: 2,
            vertical: true
        };
        vm.sliderEnergy = {
            value: 0.5
        };
        vm.sliderDancability = {
            value: 0.5
        };
        vm.sliderMood = {
            value: 0.5
        };
        vm.sliderHottness = {
            value: 0.5
        };

        vm.nextStep = function () {
            vm.step += 1;
        };

        vm.prevStep = function () {
            vm.step -= 1;
        };

        vm.searchUsers = function (val) {
            return $http.get('api/users/contains/' + val).then(function (response) {
                return response.data;
            })
        };

        vm.searchTracks = function (val) {
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
                        id: items[i].id,
                        uri: items[i].uri,
                        artists: artists,
                        name: items[i].name,
                        album: items[i].album.name
                    });
                }
                return list;
            })
        };

        vm.addTrack = function () {
            if (vm.tracks.indexOf(vm.newTrack) != -1 || !vm.newTrack) {
                return;
            }
            vm.tracks.push(vm.newTrack);
            vm.newTrack = null;
            vm.tracks.sort(function (a, b) {
                var left = a.artists.toLowerCase();
                var right = b.artists.toLowerCase();
                return left > right ? 1 : left < right ? -1 : 0;
            })
        };

        vm.addUser = function () {
            if (vm.users.indexOf(vm.newUser) != -1 || !vm.newUser) {
                return;
            }
            vm.users.push(vm.newUser);
            vm.newUser = null;
            vm.users.sort(function (a, b) {
                var left = a.login.toLowerCase();
                var right = b.login.toLowerCase();
                return left > right ? 1 : left < right ? -1 : 0;
            })
        };

        vm.deleteTrack = function (index) {
            vm.tracks.splice(index, 1);
        };

        vm.deleteUser = function (index) {
            vm.users.splice(index, 1);
        };

        vm.onSelectTrack = function ($item, $model, $label) {
            vm.newTrack = $item;
        };

        vm.onSelectUser = function ($item, $model, $label) {
            vm.newUser = $item;
        };

        vm.generate = function () {
            var dataObj = {
                users : vm.users.map(function (user) {
                    return user.login;
                }),
                energy : vm.sliderEnergy.value,
                dancability : vm.sliderDancability.value,
                mood : vm.sliderMood.value,
                hottness : vm.sliderHottness.value
            };
            var result = null;

            $http({
                method : 'POST',
                url : 'http://localhost:5000/api/playlist',
                data: dataObj,
                headers : {'Content-Type': 'application/json'}
            }).then(function (response) {
                result = response.data;
            });

            $state.go('player', {tracks:result});
        }
    }
})();