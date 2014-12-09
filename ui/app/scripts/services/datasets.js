"use strict";

/**
 * @ngdoc service
 * @name telApp.Datasets
 * @description
 * #  Datasets
 * Service in the telApp.
 */
angular.module("telApp")
    .service("Datasets", ["$http", "bmApiUrls", "bmCookies", "$rootScope",
        function ($http, bmApiUrls, bmCookies, $rootScope) {

            return {
                getDatasets: function (params) {
                    return $http.get(bmApiUrls.getUrl("tel", "datasets"), {
                        params: params
                    });
                },
                getDataset: function (id, params) {
                    return $http.get(bmApiUrls.getUrl("tel", "datasets") + "/" + encodeURIComponent(id), {
                        params: params
                    });
                }
            };
        }]);
