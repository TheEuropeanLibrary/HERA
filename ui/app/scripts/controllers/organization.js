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
            $scope.editMode = false;

            $scope.toggleEditMode = function () {
                $scope.editMode = true;
            };

            $scope.cancelEdit = function () {
                $scope.editMode = false;
            };

            $scope.getProvider = function () {
                Providers
                    .getProvider($scope.selectedProviderId)
                    .then(function (data) {
                        $scope.selectedProvider = data.data;
                    });
            };

            $scope.accordionStatuses = [];

            Providers
                .getProviders()
                .then(function (data) {
                    $scope.providers = data.data.results;
                });
        }
    ]);
