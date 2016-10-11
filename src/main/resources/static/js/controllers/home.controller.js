/**
 * Created by Roksana on 2016-10-10.
 */
(function () {
    'use strict'

    angular
        .module('weplay')
        .controller('home', HomeController);

    function HomeController(){
        var vm = this;
        console.log("start");
    }
})();