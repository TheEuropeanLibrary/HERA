"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:OrganizationCtrl
 * @description
 * # OrganizationCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("OrganizationCtrl", ["$scope", "Providers", "telEnums", "$q", "$modal",
        function ($scope, Providers, telEnums, $q, $modal) {

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

                Object.keys($scope.accordionStatuses).forEach(function (i) {
                    if ($scope.accordionStatuses[i]) {
                        $scope.getData(i);
                    }
                });

                return data;
            }

            var forms = ["general", "contact", "portal", "image"],
                dataCopy = {};

            $scope.editMode = false;
            $scope.accordionStatuses = {
                general: true
            };
            $scope.data = {};
            $scope.editMode = {};

            $scope.toggleEditMode = function (type) {
                $scope.editMode[type] = !$scope.editMode[type];
            };

            $scope.cancelEdit = function (type) {
                $scope.editMode[type] = !$scope.editMode[type];
                $scope.data[type] = angular.copy(dataCopy[type]);
            };

            $scope.addName = function () {
                $scope.data.general.Name.push({});
            };

            $scope.removeName = function (index) {
                $scope.data.general.Name.splice(index, 1);
            };

            $scope.addLink = function () {
                $scope.data.portal.Link.push({});
            };

            $scope.removeLink = function (index) {
                $scope.data.portal.Link.splice(index, 1);
            };

            $scope.saveChanges = function (type) {
                //if ($scope.parentForm[type].$valid && $scope.data[type]) {
                    Providers.updateProvider($scope.data[type], {
                            filter: type
                        })
                        .then(function () {
                            if ($scope.parentForm[type]) {
                                $scope.parentForm[type].$setPristine();
                            }
                            if ($scope.editMode[type]) {
                                $scope.editMode[type] = false;
                            }
                            $scope.getData(type);
                        });
                //}
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
                            if ($scope.data.general && !$scope.data.general.Name) {
                                $scope.data.general.Name = [{}];
                            }
                            if ($scope.data.portal && !$scope.data.portal.Link) {
                                $scope.data.portal.Link = [{}];
                            }
                            console.log($scope.data);
                        });
                }
            };

            $scope.changeProvider = function () {
                Providers.getProvider($scope.selectedProviderId)
                    .then(parseProvider);
            };

            $scope.addImage = function () {
                var modalInstance = $modal.open({
                    templateUrl: "views/add-image-modal.html",
                    controller: "AddImageModalCtrl",
                    windowClass: "tel-modal",
                    backdropClass: "tel-modal-backdrop"
                });

                modalInstance.result.then(function (imageUrl) {
                    if (!$scope.data.image.Image) {
                        $scope.data.image.Image = [];
                    }
                    $scope.data.image.Image.push(imageUrl);
                    $scope.saveChanges("image");
                });
            };

            $scope.deleteImage = function (index) {

                var modalInstance = $modal.open({
                    templateUrl: "views/remove-image-modal.html",
                    controller: "RemoveImageModalCtrl",
                    windowClass: "tel-modal",
                    backdropClass: "tel-modal-backdrop"
                });

                modalInstance.result.then(function () {
                    $scope.data.image.Image.splice(index, 1);
                    $scope.saveChanges("image");
                });
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

            telEnums.getPortalStatuses().then(function (portalStatuses) {
                $scope.portalStatuses = portalStatuses;
            });

            telEnums.getLinkTypes().then(function (linkTypes) {
                $scope.linkTypes = linkTypes;
            });

            telEnums.getMembershipTypes().then(function (membershipTypes) {
                $scope.membershipTypes = membershipTypes;
            });

            telEnums.getLibraryOrganisations().then(function (libraryOrganisations) {
                $scope.libraryOrganisations = libraryOrganisations;
            });

            telEnums.getConsortiumTypes().then(function (consortiumTypes) {
                $scope.consortiumTypes = consortiumTypes;
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
                .then(parseProviders)
                .then(function () {
                    if ($scope.providers && $scope.providers.length) {
                        $scope.selectedProviderId = $scope.providers[0].id;
                    }
                    return Providers.getProvider($scope.selectedProviderId);
                })
                .then(parseProvider);
        }
    ]);
