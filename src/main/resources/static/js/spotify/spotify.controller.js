/**
 * Created by P on 18.11.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('SpotifyController', SpotifyController);


    SpotifyController.$inject = ['Spotify'];

    function SpotifyController(Spotify) {
        var vm = this;

        vm.loginWithSpotify = loginWithSpotify;

        function loginWithSpotify() {
            Spotify.login();
        }
    }
})();