"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:DatasetsCtrl
 * @description
 * # DatasetsCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("DatasetsCtrl", ["$scope", "Providers", "telEnums", "$q", "$modal", "Datasets",
        function ($scope, Providers, telEnums, $q, $modal, Datasets) {

            function parseProviders(data) {

                if (data.data.results && data.data.results.length) {
                    $scope.providers = data.data.results.map(function (provider) {
                        return {
                            id: provider.id,
                            name: provider.Name.filter(function (name) {
                                return name.NameType == "MAIN";
                            })[0].Value
                        };
                    });
                }

                return data;
            }

            function parseProvider(data) {
                $scope.selectedProvider = {};
                $scope.selectedProvider.Identifier = data.data.Identifier;
                $scope.selectedProvider.logo = (data.data.Link.filter(function (link) {
                    return link.LinkType == "LOGO";
                })[0] || {}).Value;

                //Object.keys($scope.accordionStatuses).forEach(function (i) {
                //    if ($scope.accordionStatuses[i]) {
                //        $scope.getData(i);
                //    }
                //});

                return data;
            }

            $scope.accordionStatuses = {};
            $scope.editMode = {};
            $scope.data = {};
            $scope.datepickers = {};

            $scope.toggleEditMode = function (type) {
                $scope.editMode[type] = !$scope.editMode[type];
            };

            $scope.cancelEdit = function (type) {
                $scope.editMode[type] = !$scope.editMode[type];
                //$scope.data[type] = angular.copy(dataCopy[type]);
            };

            $scope.openDatepicker = function ($e, type) {
                $e.preventDefault();
                $e.stopPropagation();
                $scope.datepickers[type] = true;
            };

            Providers
                .getProviders()
                .then(parseProviders)
                .then(function () {
                    if ($scope.providers && $scope.providers.length) {
                        $scope.selectedProviderId = $scope.providers[0].id;
                    }
                    return Providers.getProvider($scope.selectedProviderId);
                })
                .then(parseProvider)
                .then(function () {
                    return Datasets.getDatasets({
                        providerId: $scope.selectedProviderId
                    });
                })
                .then(function (data) {

                });
        }
    ]);
