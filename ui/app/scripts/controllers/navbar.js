"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:NavBarCtrl
 * @description
 * # NavBarCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("NavBarCtrl", ["$scope", "bmCookies", "$state",
        function ($scope, bmCookies, $state) {
            $scope.logout = function ($e) {
                $e.preventDefault();
                $e.stopPropagation();
                bmCookies.removeItem("Authorization");
                $state.go("signin");
            };
        }
    ]);
