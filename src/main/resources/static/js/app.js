(function () {
    'use strict';

    angular
        .module('weplay', [ 'ui.bootstrap', 'ngResource', 'ui.router', 'ngStorage', 'spotify', 'ngSanitize', 'rzModule']).run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();
