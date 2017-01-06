/**
 * Created by P on 19.11.2016.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('CallbackController', CallbackController);


    CallbackController.$inject = ['$location', '$http', '$window', 'Principal'];

    function CallbackController($location, $http, $window, Principal) {
        var vm = this;
        vm.account = null;
        vm.access_token = $location.search().access_token;
        vm.token_type = $location.search().token_type;
        vm.expires_in = $location.search().expires_in;

        Principal.identity().then(function(account) {
            vm.account = account;
            console.log(vm.account.login);
        }).then(function () {
            return $http.post('http://localhost:5000/api/spotify/sync/' + vm.access_token + '/' + vm.expires_in + '/' + vm.account.login)
                .then(function (response) {
                    $window.close();
                    $window.alert('Success!');
                    return response.data;
                }, function (response) {
                    $window.close();
                    $window.alert('Failure!');
                    return response.data;
                });
        });
    }
})();