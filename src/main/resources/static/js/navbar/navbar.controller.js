(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('navigation', navigation);

    navigation.$inject = ['$location', '$route'];

    function navigation($location, $route) {
console.log("navigation");
        var vm = this;
        vm.tab = tab;
    //     vm.login = login;
    //     vm.logout = logout;
    //     vm.authenticated = isAuthenticated;
    //     vm.clear = clear;
    //
    //     vm.credentials = {
    //         login: '',
    //         password: ''
    //     };
    //
    //     function isAuthenticated() {
    //         return AuthenticationService.isAuthenticated();
    //     }
    //
        function tab(route) {
            return $route.current && route === $route.current.controller;
        }
    //
    //     function login() {
    //         AuthenticationService.login(vm.credentials, function (authenticated) {
    //             console.log("login"+!!authenticated);
    //             if (authenticated) {
    //                 console.log("Login succeeded");
    //                 // $location.path("/");
    //                 PreviousState.goToLastState();
    //                 vm.error = false;
    //             } else {
    //                 console.log("Login failed");
    //                 $location.path("/login");
    //                 vm.error = true;
    //             }
    //             // console.log(vm.authenticated());
    //
    //         });
    //     }
    //
    //     function logout() {
    //         AuthenticationService.logout();
    //         $location.path("/");
    //         // console.log(vm.authenticated());
    //
    //     }
    //
    //     function clear() {
    //         planService.setPreferences({},{});
    //         planService.setTravel({});
    //     }
    }
})();
