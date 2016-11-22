(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'LoginService', 'Principal'];

    function NavbarController($state, Auth, LoginService, Principal) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = login;
        vm.loginWithSpotify = loginWithSpotify;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function loginWithSpotify() {
            $state.go('spotify_login');
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }
    }
})();
