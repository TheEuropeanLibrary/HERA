"use strict";

/**
 * @ngdoc service
 * @name telApp.Providers
 * @description
 * # Providers
 * Service in the telApp.
 */
angular.module("telApp")
    .service("Providers", ["$http", "bmApiUrls",
        function ($http, bmApiUrls) {
            return {
                getProviders: function () {
                    return $http.get(bmApiUrls.getUrl("tel", "providers"));
                },
                getProvider: function (id) {
                    return $http.get(bmApiUrls.getUrl("tel", "providers") + "/" + encodeURIComponent(id));
                }
            }
        }
    ]);
