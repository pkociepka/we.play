(function() {
    'use strict';

    angular
        .module('weplay')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage' ];

    function AuthServerProvider ($http, $localStorage ) {
        return {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            logout: logout
        };

        function getToken () {
            return $localStorage.authenticationToken;
        }

        function hasValidToken () {
            var token = this.getToken();
            return !!token;
        }

        function login (credentials) {
            var data = "username=" + encodeURIComponent(credentials.username) +
                "&password=" + encodeURIComponent(credentials.password) +
                '&remember-me=' + credentials.rememberMe + '&submit=Login';

            return $http.post('api/authentication', data, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function (response) {
                return response.data;
            });
        }

        function logout () {
            // logout from the server
            $http.post('api/logout').then(function (response) {
                delete $localStorage.authenticationToken;
                // to get a new csrf token call the api
                $http.get('api/account');
                return response.data;
            });
        }
    }
})();
