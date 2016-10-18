(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('navigation', navigation);

    navigation.$inject = ['$route'];

    function navigation($route) {
        var vm = this;
        vm.tab = tab;

        function tab(route) {
            return $route.current && route === $route.current.controller;
        }
    }
})();
