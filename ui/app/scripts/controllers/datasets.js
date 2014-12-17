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
                            if (type == "data" && !$scope.data.data.DataFormat) {
                                $scope.data.data.DataFormat = [{}];
                            }
                            if (type == "portal") {
                                if (!$scope.data.portal.Link) {
                                    $scope.data.portal.Link = [{
                                        LinkType: "test",
                                        Value: "test"
                                    }];
                                }
                                if (!$scope.data.portal.Note) {
                                    $scope.data.portal.Note = [{
                                        NoteType: "test",
                                        Value: "test"
                                    }];
                                }
                            }
                            console.log($scope.data);
                        });
                }
            };

            $scope.addDataType = function () {
                if ($scope.data.data.DataFormat) {
                    $scope.data.data.DataFormat.push({});
                }
            };

            $scope.removeDataType = function (index) {
                $scope.data.data.DataFormat.splice(index, 1);
            };

            $scope.addLink = function () {
                if ($scope.data.portal.Link) {
                    $scope.data.portal.Link.push({});
                }
            };

            $scope.removeLink = function (index) {
                $scope.data.portal.Link.splice(index, 1);
            };

            $scope.addDescription = function () {
                if ($scope.data.description.CollectionDescription) {
                    $scope.data.description.CollectionDescription.push({});
                }
            };

            $scope.removeDescription = function (index) {
                $scope.data.description.CollectionDescription.splice(index, 1);
            };

            $scope.addNote = function () {
                if ($scope.data.portal.Note) {
                    $scope.data.portal.Note.push({});
                }
            };

            $scope.removeNote = function (index) {
                $scope.data.portal.Note.splice(index, 1);
            };

            $scope.saveChanges = function (type) {
                //if ($scope.parentForm[type].$valid && $scope.data[type]) {
                Datasets.updateDataset($scope.data[type], {
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

            $scope.$watch("accordionStatuses", function (newVal, oldVal) {
                var type;
                for (type in newVal) {
                    if (newVal[type] && newVal[type] != oldVal[type]) {
                        $scope.getData(type);
                    }
                }
            }, true);

            telEnums.getDatasetTypes().then(function (data) {
                $scope.datasetTypes = data;
            });

            telEnums.getIngestionStatuses().then(function (data) {
                $scope.ingestionStatuses = data;
            });

            telEnums.getAgreementStatuses().then(function (data) {
                $scope.agreementStatuses = data;
            });

            telEnums.getDistributionFormats().then(function (data) {
                $scope.distributionFormats = data;
            });

            telEnums.getSourceLicenses().then(function (data) {
                $scope.sourceLicenses = data;
            });

            telEnums.getDistributionLicenses().then(function (data) {
                $scope.distributionLicenses = data;
            });

            telEnums.getDigitisationStatuses().then(function (data) {
                $scope.digitisationStatuses = data;
            });

            telEnums.getDataTypes().then(function (data) {
                $scope.dataTypes = data;
            });

            telEnums.getPortalStatuses().then(function (data) {
                $scope.portalStatuses = data;
            });

            telEnums.getDatasetLinkTypes().then(function (data) {
                $scope.datasetLinkTypes = data;
            });

            telEnums.getNoteTypes().then(function (data) {
                $scope.noteTypes = data;
            });

            telEnums.getDisciplines().then(function (data) {
                $scope.disciplines = data;
            });

            telEnums.getSpatialCoverages().then(function (data) {
                $scope.spatialCoverages = data;
            });

            telEnums.getTimeCoverages().then(function (data) {
                $scope.timeCoverages = data;
            });

            telEnums.getItemTypes().then(function (data) {
                $scope.itemTypes = data;
            });

            telEnums.getSubjects().then(function (data) {
                $scope.subjects = data;
            });

            telEnums.getLanguages().then(function (data) {
                $scope.languages = data;
            });

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
                    }
                    $scope.accordionStatuses.general = true;
                });
                //.then();
        }
    ]);
