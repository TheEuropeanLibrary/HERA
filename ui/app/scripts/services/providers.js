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
                getProvider: function (id, params) {
                    return $http.get(bmApiUrls.getUrl("tel", "providers") + "/" + encodeURIComponent(id), {
                        params: params
                    });
                },
                updateProvider: function (provider, params) {
                    return $http.put(bmApiUrls.getUrl("tel", "providers") + "/" + encodeURIComponent(provider.id), provider, {
                        params: params
                    });
                }
            }
        }
    ]);
