"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:OrganizationCtrl
 * @description
 * # OrganizationCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("OrganizationCtrl", ["$scope", "Providers", "telEnums", "$q",
        function ($scope, Providers, telEnums, $q) {

            function parseProvider(data) {
                $scope.selectedProvider = data.data;

                if (!$scope.selectedProvider["Name"]) {
                    $scope.selectedProvider["Name"] = [{}];
                }

                return data;
            }

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

            var forms = ["general", "contact"],
                dataCopy = {};

            $scope.editMode = false;
            $scope.accordionStatuses = {};
            $scope.data = {};

            $scope.toggleEditMode = function () {
                $scope.editMode = true;
            };

            $scope.cancelEdit = function () {
                $scope.editMode = false;
                $scope.data = angular.copy(dataCopy);
            };

            $scope.getProvider = function () {
                Providers
                    .getProvider($scope.selectedProviderId)
                    .then(parseProvider);
            };

            $scope.addName = function () {
                $scope.data.general.Name.push({});
            };

            $scope.removeName = function (index) {
                $scope.data.general.Name.splice(index, 1);
            };

            $scope.saveChanges = function () {
                var promises = [];

                forms.forEach(function (formName) {
                    if ($scope.parentForm[formName].$valid && $scope.data[formName]) {
                        console.log(formName, $scope.data[formName]);
                        promises.push(Providers.updateProvider($scope.data[formName], {
                                filter: formName
                            })
                        );
                    }
                });

                $q.all(promises).then(function () {
                    $scope.parentForm.$setPristine();
                    $scope.editMode = false;
                });
            };

            $scope.getData = function (type) {
                if ($scope.selectedProviderId) {
                    Providers
                        .getProvider($scope.selectedProviderId, {
                            filter: type
                        })
                        .then(function (data) {
                            $scope.data[type] = data.data;
                            dataCopy = angular.copy($scope.data);
                        });
                }
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

            telEnums.getProviderTypes().then(function (providerTypes) {
                $scope.providerTypes = providerTypes;
            });

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
                .then(parseProviders);
        }
    ]);
