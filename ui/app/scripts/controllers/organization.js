"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:OrganizationCtrl
 * @description
 * # OrganizationCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("OrganizationCtrl", ["$scope", "Providers", "telEnums",
        function ($scope, Providers, telEnums) {

            function parseProvider(data) {
                $scope.selectedProvider = data.data;
                if (!$scope.selectedProvider["Names"]) {
                    $scope.selectedProvider["Names"] = [{}];
                }
                return data;
            }

            $scope.editMode = false;
            $scope.accordionStatuses = [];

            $scope.toggleEditMode = function () {
                $scope.editMode = true;
            };

            $scope.cancelEdit = function () {
                $scope.editMode = false;
            };

            $scope.getProvider = function () {
                Providers
                    .getProvider($scope.selectedProviderId)
                    .then(parseProvider);
            };

            $scope.addName = function () {
                $scope.selectedProvider["Names"].push({});
            };

            $scope.removeName = function (index) {
                $scope.selectedProvider["Names"].splice(index, 1);
            };

            telEnums.getLanguages().then(function (languages) {
                $scope.languages = languages;
            });

            telEnums.getCountries().then(function (countries) {
                $scope.countries = countries;
            });

            telEnums.getNameTypes().then(function (nameTypes) {
                $scope.nameTypes = nameTypes;
            });

            Providers
                .getProviders()
                .then(function (data) {
                    $scope.providers = data.data.results;
                });
        }
    ]);
