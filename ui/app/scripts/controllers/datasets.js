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

            var dataCopy;

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

            $scope.getData = function (type) {
                if ($scope.selectedDatasetId) {
                    Datasets
                        .getDataset($scope.selectedDatasetId, {
                            filter: type
                        })
                        .then(function (data) {
                            $scope.data[type] = data.data;
                            dataCopy = angular.copy($scope.data);
                            console.log($scope.data);
                        });
                }
            };

            $scope.$watch("accordionStatuses", function (newVal, oldVal) {
                var type;
                for (type in newVal) {
                    if (newVal[type] && newVal[type] != oldVal[type]) {
                        $scope.getData(type);
                    }
                }
            }, true);

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
                    $scope.datasets = data.data.results.map(function (dataset) {
                        return {
                            id: dataset.id,
                            name: dataset.Name[0].Value
                        }
                    });
                    if ($scope.datasets && $scope.datasets.length) {
                        $scope.selectedDatasetId = $scope.datasets[0].id;
                        return Datasets.getDataset($scope.selectedDatasetId);
                    }
                });
                //.then();
        }
    ]);
