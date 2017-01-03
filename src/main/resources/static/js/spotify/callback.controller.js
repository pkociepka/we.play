/**
 * Created by P on 19.11.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('CallbackController', CallbackController);


    CallbackController.$inject = ['$location', '$http', '$window'];

    function CallbackController($location, $http, $window) {
        var vm = this;
        vm.access_token = $location.search().access_token;
        vm.token_type = $location.search().token_type;
        vm.expires_in = $location.search().expires_in;

        return $http.post('/api/spotifytoken/' + vm.access_token + '/' + vm.expires_in).then(function (response) {
            $window.close();
            $window.alert('Success!');
            return response.data;
        });
    }
})();