/**
 * Created by Roksana on 2016-10-10.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Auth', 'Principal', 'LoginService', '$state'];

    function HomeController ($scope, Auth, Principal, LoginService, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.logout = logout;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function logout() {
            Auth.logout();
            $state.go('home');
        }

        function register () {
            $state.go('register');
        }
    }
})();