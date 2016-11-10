(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('navigation', navigation);

    navigation.$inject = ['$route', 'LoginService'];

    function navigation($route, LoginService) {
        var vm = this;
        vm.tab = tab;
        vm.login = LoginService.open;

        function tab(route) {
            return $route.current && route === $route.current.controller;
        }
    }
})();
