/**
 * Created by P on 19.11.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('CallbackController', CallbackController);


    CallbackController.$inject = ['$location'];

    function CallbackController($location) {
        var vm = this;
        console.log('search', $location.search());
        vm.access_token = $location.search().access_token;
        vm.token_type = $location.search().token_type;
        vm.expires_in = $location.search().expires_in;
    }
})();