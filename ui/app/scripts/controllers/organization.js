"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:OrganizationCtrl
 * @description
 * # OrganizationCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("OrganizationCtrl", ["$scope", "Providers",
        function ($scope, Providers) {
            $scope.accordionStatuses = [];
            Providers.getProviders();
        }
    ]);
