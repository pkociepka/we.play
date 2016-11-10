(function() {
    'use strict';

    angular
        .module('weplay')
        .factory('Auth', Auth);

    Auth.$inject = ['$rootScope', '$state', '$q', 'AuthServerProvider', 'Principal', 'LoginService', 'Register'];

    function Auth ($rootScope, $state, $q, AuthServerProvider, Principal, LoginService, Register) {
        console.log("Auth service");

        var service =  {
            authorize: authorize,
            createAccount: createAccount,
            login: login,
            logout: logout
        };

        return service;

        function authorize (force) {
            return Principal.identity(force).then(authThen);

            function authThen () {
                var isAuthenticated = Principal.isAuthenticated();

                // an authenticated user can't access to login and register pages
                if (isAuthenticated && $rootScope.toState.parent === 'account' && ($rootScope.toState.name === 'login' || $rootScope.toState.name === 'register')) {
                    $state.go('home');
                }

                if ($rootScope.toState.data.authorities && $rootScope.toState.data.authorities.length > 0 && !Principal.hasAnyAuthority($rootScope.toState.data.authorities)) {
                    if (isAuthenticated) {
                        // user is signed in but not authorized for desired state
                        $state.go('accessdenied');
                    }
                    else {
                        // user is not authenticated. stow the state they wanted before you
                        // send them to the login service, so you can return them when you're done
                        $rootScope.redirected = true;
                        $rootScope.previousStateName = $rootScope.toState;
                        $rootScope.previousStateNameParams = $rootScope.toStateParams;

                        // now, send them to the signin state so they can log in
                        $state.go('accessdenied');
                        LoginService.open();
                    }
                }
            }
        }

        function createAccount (account, callback) {
            var cb = callback || angular.noop;

            return Register.save(account,
                function () {
                    return cb(account);
                },
                function (err) {
                    this.logout();
                    return cb(err);
                }.bind(this)).$promise;
        }

        function login (credentials, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            AuthServerProvider.login(credentials)
                .then(loginThen)
                .catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

            function loginThen (data) {
                Principal.identity(true).then(function(account) {
                    deferred.resolve(data);
                });
                return cb();
            }

            return deferred.promise;
        }

        function logout () {
            AuthServerProvider.logout();
            Principal.authenticate(null);

            // Reset state memory if not redirected
            if(!$rootScope.redirected) {
                $rootScope.previousStateName = undefined;
                $rootScope.previousStateNameParams = undefined;
            }
        }
    }
})();
