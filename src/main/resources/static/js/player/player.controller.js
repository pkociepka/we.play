/**
 * Created by Comarch on 2016-12-14.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('PlayerController', PlayerController);


    PlayerController.$inject = ['$sce', '$stateParams'];

    function PlayerController($sce, $stateParams) {
        var vm = this;
        vm.frameSrc = $sce.trustAsResourceUrl('https://embed.spotify.com/?uri=spotify:trackset:WePlay:' + $stateParams.songs);
    }
})();