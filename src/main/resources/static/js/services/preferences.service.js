/**
 * Created by Roksana on 2016-10-12.
 */
(function () {
    'use strict';

    angular
        .module('weplay')
        .factory('PreferencesFactory', PreferencesFactory);

    PreferencesFactory.$inject = ['$http'];
    
    function PreferencesFactory($http) {
        var friends = [];
        var songs = [];
        var activity;

        var service = {
            getFriends: getFriends,
            addNewFriend: addNewFriend,
            addNewSong: addNewSong,
            setActivity: setActivity,
            sendPreferences: sendPreferences
        };

        return service;

        function getFriends() {
            return friends;
        }

        function addNewFriend(friend) {
            if (friend != null)
                friends.push(friend);
        }

        function addNewSong(song) {
            if (song != null)
                songs.push(song);
        }

        function setActivity(activ) {
            console.log("Activity" + activity + activ)
            if (activ != null)
                return activity = activ.toUpperCase();
        }

        function sendPreferences(params) {
            var preferences = {
                friends: friends,
                songs: songs,
                activity: activity,
                params: params
            };
            
            console.log(preferences);
            $http.post("preferences",
                preferences
            ).then(function (response) {
                return response;
            })

        }
    }
})
();
